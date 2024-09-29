package com.conduit.util;


import com.conduit.request.ApiTestBase;
import com.conduit.request.Pojo.Request.LoginUserPayload;
import com.conduit.request.Pojo.Request.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class tokenUtil extends ApiTestBase {

    //static String token;
    static final ThreadLocal<String> token = ThreadLocal.withInitial(() -> "");
    static Response response;
    public static ThreadLocal<String> getToken() throws IOException {

        {
            String projectRootDir = System.getProperty("user.dir");
            // Append the relative path to the resources folder and the CSV file
            String userCsvFilePath = projectRootDir + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "loginUserData.csv";

            List<Map<String, String>> userLogin;

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
            String fetchedToken = response.jsonPath().getString("user.token");
            //token = response.jsonPath().getString("user.token");
            System.out.println("Token:" + fetchedToken.trim());
            token.set(fetchedToken);

        }
        return token;
    }

@Test
    public void tokentest() throws IOException {
    ThreadLocal<String> token = getToken();
    System.out.println("token ==== " + token);

}

}
