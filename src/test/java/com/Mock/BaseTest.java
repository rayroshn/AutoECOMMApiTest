package com.Mock;


import com.config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeSuite
    public void setup()
    {
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(ConfigReader.getMockBaseURI())
                .setContentType(ContentType.JSON).addHeader("x-api-key","PMAK-6654b1baeeb31b0001abfda6-7ce922b6aa65f23e202cc4fde6f2d8d955").build();

        /*RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri(ConfigReader.getConduitBaseURI())
                .setContentType(ContentType.JSON).addFilter(new ResponseLoggingFilter()).addFilter(new RequestLoggingFilter()).build();*/

        RestAssured.requestSpecification =requestSpecification;

    }
}
