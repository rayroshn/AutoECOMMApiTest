package com.conduit.request.ApiTest;

import com.conduit.Constants.ErrorStatusCodes;
import com.conduit.Constants.FilePaths;
import com.conduit.ObjectManager.ApiObjectManager;
import com.conduit.request.ApiTestBase;
import com.conduit.request.Pojo.Request.CommentPayloadRequest;
import com.conduit.request.Pojo.Request.DeleteArticle;
import com.conduit.request.Response.DeleteArtcile.DeleteArticleResponsePayload;
import com.conduit.request.Response.DeleteArtcile.Errors;
import com.conduit.util.FakerData.FakerDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class DeleteArticelApiTest extends ApiTestBase {


    Response response;
    ObjectMapper objectMapper = new ObjectMapper();
    //DeleteArticle deleteArticle;
    CommentPayloadRequest commentPayloadRequest = ApiObjectManager.getCommnentApiObject();
    Faker faker = new Faker();
    String actualCommentBody = FakerDataUtil.GET_COMMENT_DATA;
    String projectRootDir = System.getProperty("user.dir");
    String userCsvFilePath = FilePaths.LOGIN_USER_DATA.getPath();
    String commentCsvFilePath = FilePaths.COMMENT_USER_DATA.getPath();

    @Test
    public void deleteValidArticleTest() throws IOException, InterruptedException {

        response = given().when().delete("/articles/" + new DeleteArticle().getSlug()).thenReturn();
        int statusCode = response.getStatusCode();
        System.out.println("statusCode = " + statusCode);
        System.out.println("response = " + response.asString());
        Assert.assertEquals(response.getStatusCode(), ErrorStatusCodes.NOCONTENT_ERROR_STATUS_204.getStatusCode());
        Thread.sleep(2000);
        // validate to check if delete record displays
        // getSingleArticleBySlugTestNegative();
    }

    @Test
    private void getSingleArticleBySlugTestNegative() throws IOException, InterruptedException {

        response = given().when().get("/articles/" + new DeleteArticle().getSlug()).thenReturn();
        Thread.sleep(2000);
        System.out.println("response.getStatusCode() = " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), ErrorStatusCodes.NOTFOUND_ERROR_STATUS_404.getStatusCode());

        List<String> expectedErrors = Arrays.asList("not found", "NOT FOUND");
        DeleteArticleResponsePayload errors = objectMapper.readValue(response.getBody().asString(), DeleteArticleResponsePayload.class);
        Assert.assertEquals(errors.getErrors().getArticle().get(0), "not found");

        boolean foundMatch = false;

/*// Iterate over the expected errors and check if at least one is in the actual error messages
        for (String expectedError : expectedErrors) {
            if (errors.getErrors().getArticle().contains(expectedError)) {
                foundMatch = true;  // Set the flag if a match is found
                break;  // Exit the loop early as we need only one match
            }

// Assert that at least one expected error was found in the actual errors
            Assert.assertTrue(foundMatch, "At least one expected error message should be present in the actual errors.");


        }*/
    }
}
