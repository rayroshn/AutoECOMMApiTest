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
                .when().post("https://fakestoreapi.com/products").andReturn();

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
                .when().post("https://fakestoreapi.com/products").andReturn();

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

        String createInvalidProductRequestPayload = createNewProductRequestPayload.replace("\"title\":\"" + title + "\"", "\"title\":123");

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
        ValidatableResponse validatableResponse = given().contentType("application/json").body(productMainPayload)
            .when().post("https://fakestoreapi.com/products").then().assertThat().statusCode(201)
            .body(JsonSchemaValidator.matchesJsonSchema(createNewProductResponseSchema));

        Assert.assertNotNull(validatableResponse);
    }

    @Test(dataProvider = "getProductTemplate", dataProviderClass = ProductDataProvider.class)
    public void createNewProductResponseNegativeSchemaValidation(Product product) throws IOException {
        InputStream createNewProductResponseSchema = getClass().getClassLoader().getResourceAsStream("Product_SchemaResponse.json");
        objectMapper = new ObjectMapper();
        String productMainPayload = objectMapper.writeValueAsString(product);

        String productMainPayloadInvalid = productMainPayload.replace("\"title\":\"" + ProductFakerData.GET_PRODUCT_TITLE_DATA + "\"", "\"title\": 123");

        // Send invalid payload and validate that response does NOT match schema
        Response resp = given().contentType("application/json").body(productMainPayloadInvalid)
                .when().post("https://fakestoreapi.com/products").andReturn();

        String respBody = resp.getBody().asString();
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
