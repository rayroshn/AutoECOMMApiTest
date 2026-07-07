package com.conduit.request.ApiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class FakeStoreAuthUtil {

    /**
     * Calls fakestore /auth/login and returns token string (or empty if not found).
     */
    public static String loginAndGetToken(String username, String password) {
        JSONObject payload = new JSONObject();
        payload.put("username", username);
        payload.put("password", password);

        Response resp = given().contentType("application/json").body(payload.toString())
                .when().post("https://fakestoreapi.com/auth/login");

        if (resp == null || !(resp.getStatusCode() == 200 || resp.getStatusCode() == 201)) return "";
        JSONObject body = new JSONObject(resp.getBody().asString());
        return body.optString("token", "");
    }

    /**
     * Applies the token as a Bearer header to the current FakeStore request specification.
     * Call this after obtaining a token to make subsequent requests authenticated.
     */
    public static void applyTokenToRequestSpec(String token) {
        if (token == null || token.isEmpty()) return;
        RequestSpecification base = FakeStoreApiTestBase.getRequestSpecification();
        if (base == null) {
            RestAssured.requestSpecification = RestAssured.given().header("Authorization", "Bearer " + token);
        } else {
            RestAssured.requestSpecification = base.header("Authorization", "Bearer " + token);
        }
    }
}
