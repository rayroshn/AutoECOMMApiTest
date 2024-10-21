package com.conduit.util;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileDownloadUtil {


    public static void downloadFile(String fileUrl, String outputFilePath) throws IOException {
        // Step 1: Send GET request to download the file
        Response response = RestAssured
                .given()
                .when()
                .get(fileUrl)
                .then()
                .statusCode(200) // Ensure success response
                .extract()
                .response();

        // Step 2: Extract InputStream from the response body
        InputStream inputStream = response.asInputStream();

        // Step 3: Write the InputStream data to a file
        File outputFile = new File(outputFilePath);
        outputFile.getParentFile().mkdirs(); // Create parent directories if they don't exist

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        inputStream.close(); // Close the input stream
    }
}
