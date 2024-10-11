package com.FileIO;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class FileUploadTest  {

    private static final String BASE_URL = "https://file.io";
    private static final String FILE_PATH = "fileupload/gluco.PNG";

    private List<String> keyFiles;
    ClassLoader classLoader = getClass().getClassLoader();


    @Test
    public void testFileUploadAndRetrieval() throws IOException {
        // Create a temporary file
        File tempFile = File.createTempFile("test", ".txt");
        tempFile.deleteOnExit();

        // Write content to the file
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("This is a test");
        }

        // Upload the file
         Response response = given().contentType("multipart/form-data")
                .multiPart("file", tempFile)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("key", not(emptyOrNullString()))
                .extract().response();

        String key = response.jsonPath().getString("key");

        String fileKey = response.jsonPath().getString("key");
        keyFiles =new ArrayList<>();
         keyFiles.add(key);
        System.out.println("Uploaded file key: " + fileKey);

    /*    // Try to retrieve the file
        given()
                .when()
                .get(BASE_URL + "/" + fileKey)
                .then()
                .statusCode(200)
                .body(equalTo("This is a test")); */

        // Try to retrieve the file again (should fail as file.io links are single-use)
      /* Response response = given()
                .when()
                .get(BASE_URL + "/" + fileKey)
                .then()
                .statusCode(404)
                .body("success", equalTo(false))
                .body("error", equalTo(404))
                .body("message", equalTo("Not Found")).extract().response();*/

    }

    @Test
    public void testFileUploadAndRetrievalFromResources() {
        // Get the file from the resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File fileToUpload = new File(classLoader.getResource(FILE_PATH).getFile());
        // Ensure the file exists
        if (!fileToUpload.exists()) {
            throw new RuntimeException("File not found: " + FILE_PATH);
        }

        // Upload the file
        Response uploadResponse =given().contentType("multipart/form-data")
                .multiPart("file", fileToUpload)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("key", not(emptyOrNullString()))
                .extract().response();

        String fileKey = uploadResponse.jsonPath().getString("key");
        System.out.println("Uploaded file key: " + fileKey);

        // Try to retrieve the file
       Response response= given()
                .when()
                .get(BASE_URL + "/" + fileKey)
                .then()
                .statusCode(200)
                .body(not(emptyOrNullString())).extract().response(); // We don't know the exact content, so we just check it's not empty

        // Try to retrieve the file again (should fail as file.io links are single-use)
        /*given()
                .when()
                .get(BASE_URL + "/" + fileKey)
                .then()
                .statusCode(404)
                .body("success", equalTo(false))
                .body("error", equalTo(404))
                .body("message", equalTo("Not Found"));*/

        if(response.statusCode() ==404)
        {
            response.then().body("success",equalTo(false))
                    .body("error",equalTo(404))
                    .body("message",equalTo("Not Found"));
        }
    }

    @Test
    public void testMultiFileUploadAndRetrievalFromResources() {
        // Get the file from the resources folder


        final String[] filepaths =
                {
                        "fileupload/gluco.PNG", "fileupload/gluco1.PNG",
                        "fileupload/gluco3.PNG"

                };
        //File fileToUpload = new File(classLoader.getResource(FILE_PATH).getFile());

        // Ensure the file exists
       /* if (!fileToUpload.exists()) {
            throw new RuntimeException("File not found: " + FILE_PATH);
        }*/

        // Upload the file

            List<String> uploadResponse = uploadmultifiles(filepaths);
            List<String> getKeys = new ArrayList<>();
              for(String verifyFiles :uploadResponse)
              {
                  int count =0;
                  Response response = given().contentType("multipart/form-data").when().get(BASE_URL + "/" + verifyFiles +"/metadata").then().extract().response();
                  String imaageKey =response.jsonPath().getString("key");
                  System.out.println("imaageKey = " + imaageKey);
                  getKeys.add(imaageKey);
              }

        Assert.assertEquals(keyFiles,getKeys);
        }

    @Test
    public void testMultiFileUploadAndRetrievalFromResourcesParallerStream() throws IOException {
        // Get the file from the resources folder
        List<String> filepaths = getFilePathsFromResourcesFolder("fileupload");

        // Call to upload API to upload any type of file
        List<String> uploadResponse = uploadmultifilesDynamicallyStream(filepaths);

        //// Call to get Metdat API to  get keys of uploaded file of any type
        List<String> getKeys = getKey(uploadResponse);

        // verify if same image is retrieved
        Assert.assertEquals(keyFiles,getKeys);
    }

    // Get method call to call metadata API "/" + verifyFiles + "/metadata
    private List<String> getKey(List<String> uploadResponse) {
        return uploadResponse.parallelStream().map(verifyFiles -> {
            Response response = given().when().get(BASE_URL + "/" + verifyFiles + "/metadata").then().extract().response();
            String imaageKey = response.jsonPath().getString("key");
            System.out.println("imaageKey = " + imaageKey);
            return imaageKey;
        }).collect(Collectors.toList());
    }

    private List<String> uploadmultifiles(String[] tempFile) {
        keyFiles = new ArrayList<>();

        for(String files : tempFile) {
            //   File fileToUpload = new File(classLoader.getResource(files).getFile());

            if (classLoader.getResource(files) == null) {
                System.err.println("File not found: " + files);
                continue;  // Skip this file and move to the next iteration
            }


            File fileToUpload = new File(classLoader.getResource(files).getFile());
            Response response = given().contentType("multipart/form-data")
                    .multiPart("file", fileToUpload)
                    .when()
                    .post(BASE_URL)
                    .then()
                    /* .statusCode(200)
                     .body("success", equalTo(true))
                     .body("key", not(emptyOrNullString()))*/
                    .extract().response();

            String key = response.jsonPath().getString("key");
            keyFiles.add(key);
        }

        return keyFiles;
    }

    private List<String> uploadmultifilesDynamicallyStream(List<String> filePaths) {
        keyFiles = new ArrayList<>();

      //  List<String> files = getFilePathsFromResourcesFolder("");

        filePaths.forEach(file -> {
            // now first convert String file or file paths to file object as its reqd. as fileObject datatype in multipart() function
            File fileToUpload = new File(file);
            Response response = given().contentType("multipart/form-data").
                    multiPart("file", fileToUpload).
                    when().
                    post(BASE_URL).
                    then().
                    statusCode(200).body("success", equalTo(true)).body("key", not(emptyOrNullString())).
                    extract().
                    response();
            String key = response.jsonPath().getString("key");
            keyFiles.add(key);
        });
        return keyFiles;


        }



    // Function to get files from folders which is part of classloader i.e resources folder
    public List<String> getFilePathsFromResourcesFolder(String folderName) throws IOException {
        // get the url path of folder
        URL resource = getClass().getClassLoader().getResource(folderName);
        if(resource == null)
        {
            throw new IllegalArgumentException("Folder Not Found" + folderName);
        }

        // Now Convert the folder URL to a File object and list all files
        File folderFile = new File(resource.getFile());
        return Files.list(Paths.get(folderFile.getPath())).
                map(path -> path.toFile().getAbsolutePath())
                .collect(Collectors.toList());
    }

}

