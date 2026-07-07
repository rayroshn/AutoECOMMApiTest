package com.FileIO;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class PATToken {
    // Encode the token using a simple algorithm
    private static final String ENCODED_TOKEN = "Z2hwX3RvNzF6V05iOQ=="; // Base64 encoded version of your token

    private String getDecodedToken() {
        return new String(Base64.getDecoder().decode(ENCODED_TOKEN));
    }

    @Test
    public void testAuthCode() {
        String responseBody = getGitHubUserInfo();
        System.out.println("Response Body: " + responseBody);
    }

    private String getGitHubUserInfo() {
        // Set the base URI for GitHub API
        RestAssured.baseURI = "https://api.github.com";

        // Example of using the personal access token to get user info
        Response response = given()
                .header("Authorization", "Bearer " + getDecodedToken()) // Use decoded token
                .header("Accept", "application/json")
                .when()
                .get("/user") // GitHub API endpoint for getting user info
                .andReturn();

        // Print the response status code and body
        System.out.println("Response Status Code: " + response.getStatusCode());
        String responseBody = response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        return responseBody;
    }
}