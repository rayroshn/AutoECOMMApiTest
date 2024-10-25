package com.FileIO;

import com.conduit.request.Response.OAuthTokenResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GitHubOAuthTest {

    @Test
    public static void getOAuthToken() throws JsonProcessingException, InterruptedException {

        // Base URI for GitHub API
        RestAssured.baseURI = "https://github.com/login/oauth/access_token";

        String AuthCode= new GitHubOAuthHeadless().getAuthCode();
        System.out.println("Retrieving AuthCode = " + AuthCode);
        // Send POST request with required headers and data
        Response response = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("client_id", "Ov23lie7ZhQOcstacB8G")  // Use your actual client_id here
                .formParam("client_secret", "1b9e38612685a72eb33f5a3d6ba88ccb556b0e8c")  // Use your actual client_secret here
                .formParam("code",AuthCode)  // Use the actual code generated from authorization step
                .when()
                .post().andReturn();


        //Using POJO
        ObjectMapper objectMapper = new ObjectMapper();

        OAuthTokenResponse tokenResponse = objectMapper.readValue(response.getBody().asString(), OAuthTokenResponse.class);

        //System.out.println("Response Body: " + response.getBody().asString());
        System.out.println("tokenResponse.getAccessToken() = " + tokenResponse.getAccessToken().trim());



         // using JSON path
        // String access_token = response.jsonPath().get("access_token").toString();
        // Print response status code and body
       // System.out.println("Access_token: " + access_token.trim());

    }
}
