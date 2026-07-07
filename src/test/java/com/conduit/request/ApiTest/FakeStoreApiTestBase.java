package com.conduit.request.ApiTest;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

public class FakeStoreApiTestBase {

    private static RequestSpecification requestSpecification;
    private static HttpServer mockServer;
    private static String mockBaseUri;

    @BeforeClass(alwaysRun = true)
    public void setupFakeStore() throws IOException {
        String configuredBaseUri = System.getProperty("fake.store.base.uri", System.getenv("FAKE_STORE_BASE_URI"));
        if (configuredBaseUri != null && !configuredBaseUri.isBlank()) {
            mockBaseUri = configuredBaseUri.endsWith("/") ? configuredBaseUri : configuredBaseUri + "/";
        } else {
            mockBaseUri = startMockServer();
        }

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(mockBaseUri)
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.requestSpecification = requestSpecification;
    }

    @AfterClass(alwaysRun = true)
    public void tearDownFakeStore() {
        if (mockServer != null) {
            mockServer.stop(0);
            mockServer = null;
        }
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }

    private String startMockServer() throws IOException {
        mockServer = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        AtomicInteger requestCount = new AtomicInteger(0);

        mockServer.createContext("/products", exchange -> {
            byte[] response;
            String requestBody = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            JSONObject requestJson = new JSONObject(requestBody.isBlank() ? "{}" : requestBody);
            Object title = requestJson.opt("title");
            if (title == null) {
                title = "mock-product";
            }
            String description = requestJson.optString("description", "mock-description");
            int id = requestCount.incrementAndGet();

            JSONObject responseJson = new JSONObject();
            responseJson.put("id", id);
            responseJson.put("title", title);
            responseJson.put("price", 0.1);
            responseJson.put("description", description);
            responseJson.put("category", "string");
            responseJson.put("image", "http://example.com");

            response = responseJson.toString().getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length);
            try (OutputStream outputStream = exchange.getResponseBody()) {
                outputStream.write(response);
            }
        });

        mockServer.setExecutor(null);
        mockServer.start();
        return "http://127.0.0.1:" + mockServer.getAddress().getPort() + "/";
    }
}
