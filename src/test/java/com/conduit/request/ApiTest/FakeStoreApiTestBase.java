package com.conduit.request.ApiTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

import java.io.PrintStream;

public class FakeStoreApiTestBase {

    private static RequestSpecification requestSpecification;

    @BeforeClass
    public void setupFakeStore() {
        // Build a simple request spec pointing to fakestoreapi
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://fakestoreapi.com/")
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();

        RestAssured.requestSpecification = requestSpecification;
    }

    public static RequestSpecification getRequestSpecification() {
        return requestSpecification;
    }
}
