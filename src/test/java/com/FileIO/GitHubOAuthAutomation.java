package com.FileIO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GitHubOAuthAutomation {
    private static final String CLIENT_ID = "Ov23lie7ZhQOcstacB8G"; // Replace with your client ID
    private static final String CLIENT_SECRET = "1b9e38612685a72eb33f5a3d6ba88ccb556b0e8c   "; // Replace with your client secret
    private static final String REDIRECT_URI = "https://github.com/callback"; // Your redirect URI
    private static final String AUTHORIZATION_URL = "https://github.com/login/oauth/authorize";
    private static final String TOKEN_URL = "https://github.com/login/oauth/access_token";

    public static void main(String[] args) throws Exception {
        // Step 1: Obtain the Authorization Code
        String authorizationCode = getAuthorizationCode();

        // Step 2: Exchange the Authorization Code for an Access Token
        String accessToken = getAccessToken(authorizationCode);

        // Step 3: Use the Access Token
        System.out.println("Access Token: " + accessToken);
        // You can now use accessToken to call protected GitHub APIs
    }

    private static String getAuthorizationCode() throws Exception {
        // Step 1: Open the authorization URL
        String authorizationRequestUrl = AUTHORIZATION_URL +
                "?client_id=" + CLIENT_ID +
                "&redirect_uri=" + REDIRECT_URI +
                "&scope=user";

        // Use HttpClient to send the request and receive the redirect URI
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(authorizationRequestUrl))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Here, you would capture the redirect URL that includes the authorization code.
        // Since we can't capture the browser redirect directly, you'll need to handle it manually for the first time.
        System.out.println("Open this URL in your browser to authorize:");
        System.out.println(authorizationRequestUrl);

        // Simulating user input: enter the full redirect URL after authorization
        System.out.println("Enter the full redirect URL after authorization:");
        String fullRedirectUrl = new java.util.Scanner(System.in).nextLine();

        // Extract the authorization code from the redirect URL
        String code = extractCodeFromRedirectUrl(fullRedirectUrl);
        return code;
    }

    private static String extractCodeFromRedirectUrl(String redirectUrl) {
        // Extract the authorization code from the redirect URL
        String[] params = redirectUrl.split("[?]")[1].split("&");
        for (String param : params) {
            if (param.startsWith("code=")) {
                return param.split("=")[1];
            }
        }
        throw new RuntimeException("Authorization code not found in the redirect URL.");
    }

    private static String getAccessToken(String authorizationCode) {
        // Exchange the authorization code for an access token
        Response response = RestAssured.given()
                .contentType(ContentType.URLENC)
                .formParam("client_id", CLIENT_ID)
                .formParam("client_secret", CLIENT_SECRET)
                .formParam("code", authorizationCode)
                .post(TOKEN_URL);

        // Extract the access token from the response
        String accessToken = response.jsonPath().getString("access_token");

        if (accessToken != null) {
            return accessToken;
        } else {
            throw new RuntimeException("Failed to obtain access token: " + response.getBody().asString());
        }
    }
}
