package com.conduit.request.ApiTest;

import com.conduit.request.ApiTestBase;
import com.conduit.request.ApiTest.FakeStoreApiTestBase;
import com.conduit.request.Pojo.Request.Product;
import com.conduit.util.FakerData.ProductFakerData;
import com.conduit.util.FakerData.FakerDataUtil;
import com.conduit.util.FixturesTemplates.ProductDataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class fakeStoreApiTest extends FakeStoreApiTestBase {

    Response response;
    ObjectMapper objectMapper;
    Faker faker = new Faker();
    String title = faker.name().title();
    List<String> description = FakerDataUtil.GET_DESCRIPTION_DATA;

    private Response postProduct(String payload) {
        return given().contentType("application/json").body(payload)
                .when().post("/products").andReturn();
    }

    private String requireJsonResponse(Response response, String context) {
        int status = response.statusCode();
        String body = response.getBody().asString();
        String contentType = response.getHeader("Content-Type");

        System.err.println("[" + context + "] status=" + status + ", contentType=" + contentType);
        if (status != 200 && status != 201) {
            System.err.println("[" + context + "] response body:\n" + body);
            Assert.fail("[" + context + "] Unexpected status " + status + ". Content-Type=" + contentType + ". Body=" + body);
        }
        if (contentType == null || !contentType.toLowerCase().contains("application/json")) {
            System.err.println("[" + context + "] response body:\n" + body);
            Assert.fail("[" + context + "] Expected JSON response but got Content-Type=" + contentType + ". Body=" + body);
        }
        return body;
    }

    @Step("Create new product")
    @Test
    public void createNewProduct() throws IOException {
        Product product = new Product();
        product.setDescription(description.toString());
        product.setTitle(title);
        product.setPrice(0.1);
        product.setCategory("string");
        product.setImage("http://example.com");

        objectMapper = new ObjectMapper();
        final String productMainPayload = objectMapper.writeValueAsString(product);
        response = given().contentType("application/json").body(productMainPayload)
                .when().post("/products").andReturn();

        int status = response.statusCode();
        Assert.assertTrue(status == 200 || status == 201, "Unexpected status: " + status);

        Product created = objectMapper.readValue(response.getBody().asString(), Product.class);
        assertEquals(created.getDescription(), description.toString());
    }

    @Test(dataProvider = "getProductTemplate", dataProviderClass = ProductDataProvider.class)
    public void createNewProductViaTemplate(Product product) throws IOException {
        objectMapper = new ObjectMapper();
        final String productMainPayload = objectMapper.writeValueAsString(product);
        response = given().contentType("application/json").body(productMainPayload)
                .when().post("/products").andReturn();

        int status = response.statusCode();
        Assert.assertTrue(status == 200 || status == 201, "Unexpected status: " + status);

        Product created = objectMapper.readValue(response.getBody().asString(), Product.class);
        assertEquals(created.getDescription(), ProductFakerData.GET_PRODUCT_DESC_DATA);
    }

    @Test
    public void createNewProductRequestSchemaValidation() throws IOException {
        Product product = new Product();
        product.setDescription(description.toString());
        product.setTitle(title);
        product.setPrice(0.1);
        product.setCategory("string");
        product.setImage("http://example.com");

        objectMapper = new ObjectMapper();
        String createNewProductRequestPayload = objectMapper.writeValueAsString(product);

        InputStream createNewProductRequestSchema = getClass().getClassLoader().getResourceAsStream("Product_SchemaRequest.json");
        JSONObject rawSchema = new JSONObject(new String(createNewProductRequestSchema.readAllBytes()));
        Schema schema = SchemaLoader.load(rawSchema);
        JSONObject jsonObject = new JSONObject(createNewProductRequestPayload);
        schema.validate(jsonObject);
    }

    @Test
    public void createNewProductRequestNegativeSchemaValidation() throws IOException {
        Product product = new Product();
        product.setDescription(description.toString());
        product.setTitle(title);
        product.setPrice(0.1);
        product.setCategory("string");
        product.setImage("http://example.com");

        objectMapper = new ObjectMapper();
        String createNewProductRequestPayload = objectMapper.writeValueAsString(product);

        JSONObject invalidRequestJson = new JSONObject(createNewProductRequestPayload);
        invalidRequestJson.put("title", 123);
        String createInvalidProductRequestPayload = invalidRequestJson.toString();

        InputStream createNewProductRequestSchema = getClass().getClassLoader().getResourceAsStream("Product_SchemaRequest.json");
        JSONObject rawSchema = new JSONObject(new String(createNewProductRequestSchema.readAllBytes()));
        Schema schema = SchemaLoader.load(rawSchema);
        JSONObject jsonObject = new JSONObject(createInvalidProductRequestPayload);

        try {
            schema.validate(jsonObject);
            Assert.fail("Expected ValidationException was not thrown.");
        } catch (ValidationException validationException) {
            Assert.assertTrue(validationException.getMessage().contains("expected type: String, found: Integer"),
                    "Expected validation error message not found.");
        }
    }

    @Test(dataProvider = "getProductTemplate", dataProviderClass = ProductDataProvider.class)
    public void createNewProductResponseSchemaValidation(Product product) throws IOException {

        InputStream createNewProductResponseSchema = getClass().getClassLoader().getResourceAsStream("Product_SchemaResponse.json");

        objectMapper = new ObjectMapper();
        final String productMainPayload = objectMapper.writeValueAsString(product);
        Response schemaResponse = postProduct(productMainPayload);
        String schemaResponseBody = requireJsonResponse(schemaResponse, "createNewProductResponseSchemaValidation");

        JSONObject responseJson = new JSONObject(schemaResponseBody);
        Schema responseSchema = SchemaLoader.load(new JSONObject(new String(createNewProductResponseSchema.readAllBytes())));
        responseSchema.validate(responseJson);
    }

    @Test(dataProvider = "getProductTemplate", dataProviderClass = ProductDataProvider.class)
    public void createNewProductResponseNegativeSchemaValidation(Product product) throws IOException {
        InputStream createNewProductResponseSchema = getClass().getClassLoader().getResourceAsStream("Product_SchemaResponse.json");
        objectMapper = new ObjectMapper();
        String productMainPayload = objectMapper.writeValueAsString(product);

        JSONObject invalidResponsePayload = new JSONObject(productMainPayload);
        invalidResponsePayload.put("title", 123);
        String productMainPayloadInvalid = invalidResponsePayload.toString();

        // Send invalid payload and validate that response does NOT match schema
        Response resp = postProduct(productMainPayloadInvalid);
        String respBody = requireJsonResponse(resp, "createNewProductResponseNegativeSchemaValidation");
        JSONObject rawSchema = new JSONObject(new String(createNewProductResponseSchema.readAllBytes()));
        Schema schema = SchemaLoader.load(rawSchema);
        JSONObject jsonObject = new JSONObject(respBody);

        try {
            schema.validate(jsonObject);
            Assert.fail("Expected ValidationException was not thrown for invalid response.");
        } catch (ValidationException ve) {
            // expected: response does not conform to schema
            Assert.assertTrue(ve.getMessage().toLowerCase().contains("expected type") || ve.getMessage().toLowerCase().contains("does not match"));
        }
    }
}
