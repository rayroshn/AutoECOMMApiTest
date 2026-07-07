package com.conduit.request.ApiTest;

import com.conduit.request.Pojo.Request.Product;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class fakeStoreSampleTest extends FakeStoreApiTestBase {

    private static final String LOGIN_USERNAME = "mor_2314";
    private static final String LOGIN_PASSWORD = "83r5^_";

    @Test
    public void loginToFakeStore() {
        String token = FakeStoreAuthUtil.loginAndGetToken(LOGIN_USERNAME, LOGIN_PASSWORD);
        Assert.assertNotNull(token, "Login token should not be null");
        Assert.assertFalse(token.isEmpty(), "Login token should not be empty");
    }

    @Test(dependsOnMethods = "loginToFakeStore")
    public void addNewProductAfterLogin() {
        String token = FakeStoreAuthUtil.loginAndGetToken(LOGIN_USERNAME, LOGIN_PASSWORD);
        Assert.assertFalse(token.isEmpty(), "Login token should not be empty before creating product");
        FakeStoreAuthUtil.applyTokenToRequestSpec(token);

        Product product = new Product();
        product.setTitle("Sample Product");
        product.setPrice(9.99);
        product.setDescription("Sample product created via FakeStore API test");
        product.setCategory("electronics");
        product.setImage("https://example.com/sample.png");

        Response response = given().contentType("application/json").body(product)
                .when().post("/products").andReturn();

        int status = response.statusCode();
        Assert.assertTrue(status == 200 || status == 201, "Unexpected status: " + status);

        Product created = response.as(Product.class);
        Assert.assertEquals(created.getTitle(), product.getTitle());
        Assert.assertEquals(created.getCategory(), product.getCategory());
    }
}
