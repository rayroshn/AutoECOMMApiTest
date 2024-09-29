package com.conduit.request.ApiTest;

import com.conduit.Constants.FilePaths;
import com.conduit.ObjectManager.ApiObjectManager;
import com.conduit.request.ApiTestBase;
import com.conduit.request.Pojo.Request.Comment;
import com.conduit.request.Pojo.Request.CommentPayloadRequest;
import com.conduit.request.Pojo.Request.DeleteComment;
import com.conduit.request.Response.CommentPayloadResponse;
import com.conduit.util.CsvReaderUtill;
import com.conduit.util.FakerData.FakerDataUtil;
import com.conduit.util.FixturesTemplates.DataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class addCommentAPITest extends ApiTestBase {


    Response response;
    ObjectMapper objectMapper;
    CommentPayloadRequest commentPayloadRequest = ApiObjectManager.getCommnentApiObject();
    Faker faker = new Faker();
    String actualCommentBody= FakerDataUtil.GET_COMMENT_DATA;
    String projectRootDir = System.getProperty("user.dir");
    String userCsvFilePath= FilePaths.LOGIN_USER_DATA.getPath();
    String commentCsvFilePath=FilePaths.COMMENT_USER_DATA.getPath();


  /*  @BeforeClass
    public void setupToken() throws IOException {

        authToken = tokenUtil.getToken();
        FixtureFactoryLoader.loadTemplates("com.conduit.util.FixturesTemplates");
    }

    @BeforeMethod
    public void loadCommentTemplate()
    {
        FixtureFactoryLoader.loadTemplates("com.conduit.util.FixturesTemplates");

    }*/
    @Test
    public void addCommentAPITest() throws IOException {
        Comment comment = new Comment();
        comment.setBody(actualCommentBody);
       // CommentPayloadRequest commentPayloadRequest = new CommentPayloadRequest();
        commentPayloadRequest.setComment(comment);

        ObjectMapper objectMapper = new ObjectMapper();
        final String commentMainPayload = objectMapper.writeValueAsString(commentPayloadRequest);
       response= given().body(commentMainPayload)
                .when().
                       post("articles/Corporate-Research-Facilitator-4180/comments");

        CommentPayloadResponse commentPayloadResponse = objectMapper.readValue(response.getBody().asString(), CommentPayloadResponse.class);

        String expectedCommentPost = commentPayloadResponse.getComment().getBody().trim();
        assertEquals(actualCommentBody,expectedCommentPost);
        assertEquals(commentPayloadResponse.getComment().getAuthor().getUsername(), CsvReaderUtill.readCsvData(userCsvFilePath).get(0).get("username"));

    }

    // Test with Template factory approach
    @Test(dataProvider = "getComments",dataProviderClass = DataProvider.class)
    public void addCommentAPITestViaTemplate(Comment comment) throws IOException {
        // Comment comment = comment;
        //comment.setBody(actualCommentBody);
        //CommentPayloadRequest commentPayloadRequest = new CommentPayloadRequest();

        commentPayloadRequest.setComment(comment);

        ObjectMapper objectMapper = new ObjectMapper();
        final String commentMainPayload = objectMapper.writeValueAsString(commentPayloadRequest);
        response = given().body(commentMainPayload)
                .when().
                        post("articles/" + CsvReaderUtill.readCsvData(commentCsvFilePath).get(2).get("slug") + "/comments");

        CommentPayloadResponse commentPayloadResponse = objectMapper.readValue(response.getBody().asString(), CommentPayloadResponse.class);

        String expectedCommentPost = commentPayloadResponse.getComment().getBody().trim();
        assertEquals(actualCommentBody, expectedCommentPost);
        DeleteComment.getcommentId = commentPayloadResponse.getComment().getId();
        System.out.println(" DeleteCommentApiTest.commentId = " + DeleteComment.getcommentId);
        assertEquals(commentPayloadResponse.getComment().getAuthor().getUsername(), CsvReaderUtill.readCsvData(userCsvFilePath).get(0).get("username"));
        //return id;
    }



    /*// Test with Template factory approach
    @Test(dataProvider = "getComments",dataProviderClass = DataProvider.class)
    public void addCommentAPITestViaTemplateDup(Comment comment) throws IOException {
        // Comment comment = comment;
        //comment.setBody(actualCommentBody);
        //CommentPayloadRequest commentPayloadRequest = new CommentPayloadRequest();

        commentPayloadRequest.setComment(comment);

        ObjectMapper objectMapper = new ObjectMapper();
        final String commentMainPayload = objectMapper.writeValueAsString(commentPayloadRequest);
        response = given().body(commentMainPayload)
                .when().
                        post("articles/" + CsvReaderUtill.readCsvData(commentCsvFilePath).get(0).get("slug") + "/comments");

        CommentPayloadResponse commentPayloadResponse = objectMapper.readValue(response.getBody().asString(), CommentPayloadResponse.class);

        String expectedCommentPost = commentPayloadResponse.getComment().getBody().trim();
        assertEquals(actualCommentBody, expectedCommentPost);
        DeleteComment.getcommentId = commentPayloadResponse.getComment().getId();
        System.out.println(" DeleteCommentApiTest.commentId = " + DeleteComment.getcommentId);
        assertEquals(commentPayloadResponse.getComment().getAuthor().getUsername(), CsvReaderUtill.readCsvData(userCsvFilePath).get(0).get("username"));
        //return id;
    }*/
}

