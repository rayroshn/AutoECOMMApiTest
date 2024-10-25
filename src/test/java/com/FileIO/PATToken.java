package com.FileIO;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PATToken {

    String personalAccessToken = "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"; // Replace with your actual PAT

    @Test
    public void testAuthCode() {
        String responseBody = getGitHubUserInfo();
        System.out.println("Response Body: " + responseBody);
    }

    public String getGitHubUserInfo() {

        // Set the base URI for GitHub API
        RestAssured.baseURI = "https://api.github.com";

        // Example of using the personal access token to get user info
        Response response = given()
                .header("Authorization", "Bearer " + personalAccessToken) // Use the PAT here
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
