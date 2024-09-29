package com.hv.request;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.lessThan;

public class BaseTest {


@BeforeClass
public void setup()
{
    RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("http://localhost:3004")
            .setContentType(ContentType.JSON).
           addFilter( new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter()).build();

   // ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(201).build();
   RestAssured.requestSpecification=requestSpecification;
  // RestAssured.responseSpecification=responseSpecification;

}

}
