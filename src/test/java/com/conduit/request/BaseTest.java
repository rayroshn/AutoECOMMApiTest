package com.conduit.request;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup()
    {
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://api.realworld.io/api/")
                .setContentType(ContentType.JSON).build();

        /*RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://api.realworld.io/api/")
                .setContentType(ContentType.JSON).addFilter(new ResponseLoggingFilter()).addFilter(new RequestLoggingFilter()).build();*/

        RestAssured.requestSpecification =requestSpecification;

    }
}
