package com.conduit.request.ApiTest;

import com.conduit.request.BaseTest;
import com.conduit.request.Pojo.Request.LoginUserPayload;
import com.conduit.request.Pojo.Request.User;
import com.conduit.request.Response.LoginUserResponsePayload;
import com.conduit.util.CsvReaderUtil;
import com.conduit.util.CsvReaderUtill;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class loginApiTest extends BaseTest {

    String token;
    Response response;
    String projectRootDir = System.getProperty("user.dir");
    // Append the relative path to the resources folder and the CSV file
    String userCsvFilePath = projectRootDir + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "loginUserData.csv";

    List<Map<String, String>> userLogin;

    @Test
    public void loginUser() throws IOException {

        userLogin = CsvReaderUtill.readCsvData(userCsvFilePath);
        //User login details class
        User user = new User();
        user.setEmail(userLogin.get(0).get("email")).setPassword(userLogin.get(0).get("password"));
        LoginUserPayload loginUserPayload = new LoginUserPayload(user);

        // ObjectMapper for JSON to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        final String loginPayload = objectMapper.writeValueAsString(loginUserPayload);


        // API Login Response captured
        response = given().body(loginPayload).when()
                .post("/users/login").andReturn();

        // Response captured converted to string
        String responseBody = response.asString();
        System.out.println("Response Body: " + responseBody);

       /* String responseBody = response.asString();
        System.out.println("Response Body: " + responseBody);
       String token= response.jsonPath().getString("user.token");
        String token = response.jsonPath().getString("user.token");
        System.out.println("token = " + token);*/
        token = response.jsonPath().getString("user.token");
        // System.out.println("token = " + token);

    }


    @Test
    public void setGetToken() throws IOException {
        loginUser();
        //System.out.println("getToken = " + getToken.trim());

        LoginUserResponsePayload loginUserResponsePayload = new LoginUserResponsePayload();
        com.conduit.request.Response.User user = loginUserResponsePayload.getUser();

        ObjectMapper objectMapper = new ObjectMapper();
        LoginUserResponsePayload loginUserResponsePayloadString = objectMapper.readValue(response.getBody().asString(), LoginUserResponsePayload.class);

        System.out.println("user = " + loginUserResponsePayloadString.getUser().getEmail());

        Assert.assertEquals(userLogin.get(0).get("email").trim(), loginUserResponsePayloadString.getUser().getEmail().trim());

    }

}


