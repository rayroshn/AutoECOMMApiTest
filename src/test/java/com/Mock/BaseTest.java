package com.Mock;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup()
    {
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://de0a4e67-8fb6-421b-883c-caaa920b6525.mock.pstmn.io")
                .setContentType(ContentType.JSON).addHeader("x-api-key","PMAK-6654b1baeeb31b0001abfda6-7ce922b6aa65f23e202cc4fde6f2d8d955").build();

        /*RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://api.realworld.io/api/")
                .setContentType(ContentType.JSON).addFilter(new ResponseLoggingFilter()).addFilter(new RequestLoggingFilter()).build();*/

        RestAssured.requestSpecification =requestSpecification;

    }
}
