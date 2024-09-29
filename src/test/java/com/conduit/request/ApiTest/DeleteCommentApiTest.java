package com.conduit.request.ApiTest;

import com.conduit.Constants.ErrorStatusCodes;
import com.conduit.Constants.FilePaths;
import com.conduit.ObjectManager.ApiObjectManager;
import com.conduit.request.ApiTestBase;
import com.conduit.request.Pojo.Request.Comment;
import com.conduit.request.Pojo.Request.CommentPayloadRequest;
import com.conduit.request.Pojo.Request.DeleteArticle;
import com.conduit.request.Pojo.Request.DeleteComment;
import com.conduit.util.FakerData.FakerDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class DeleteCommentApiTest extends ApiTestBase {
    Response response;
    DeleteComment deleteComment= new DeleteComment();
    ObjectMapper objectMapper = new ObjectMapper();
    //DeleteArticle deleteArticle;
    CommentPayloadRequest commentPayloadRequest = ApiObjectManager.getCommnentApiObject();
    //public static Integer commentId;
    Faker faker = new Faker();
    String actualCommentBody = FakerDataUtil.GET_COMMENT_DATA;
    String projectRootDir = System.getProperty("user.dir");
    String userCsvFilePath = FilePaths.LOGIN_USER_DATA.getPath();
    String commentCsvFilePath = FilePaths.COMMENT_USER_DATA.getPath();

    @Test(dependsOnMethods = "com.conduit.request.ApiTest.addCommentAPITest.addCommentAPITestViaTemplate")
    public void deleteValidArticleCommentTest() throws IOException, InterruptedException {

       //new addCommentAPITest().addCommentAPITestViaTemplate();
        //new addCommentAPITest().addCommentAPITestViaTemplate(new Comment());
        response = given().when().delete("/articles/" + deleteComment.getSlug() + "/comments/" + deleteComment.getCommentId()).thenReturn();
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, ErrorStatusCodes.OK_STATUS_200.getStatusCode());

    }

}
