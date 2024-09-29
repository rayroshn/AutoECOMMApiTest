package com.conduit.request.ApiTest;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.conduit.Constants.FilePaths;
import com.conduit.request.ApiTestBase;
//import com.conduit.request.Pojo.Request.UpdateArticle.Article;
import com.conduit.request.Pojo.Request.UpdateArticle.Article;

//import com.conduit.request.Pojo.Request.UpdateArticle.UpdateArticleRequestPayload;
import com.conduit.request.Pojo.Request.UpdateArticle.UpdateArticleRequestPayload;
import com.conduit.request.Response.NewArticlePayloadResponse;
import com.conduit.util.FakerData.FakerDataUtil;
import com.conduit.util.FixturesTemplates.DataProvider;
import com.conduit.util.tokenUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;

public class updateArticleApiTest extends ApiTestBase {
    Response response;
    ObjectMapper objectMapper;
    com.conduit.request.Pojo.Request.UpdateArticle.UpdateArticleRequestPayload updateArticleRequestPayload;
    Faker faker = new Faker();
    String title=faker.name().title();
    List<String> description= FakerDataUtil.GET_DESCRIPTION_DATA;
    String userCsvFilePath= FilePaths.LOGIN_USER_DATA.getPath();
    //public static final Logger logger = LoggerFactory.getLogger(articleAPITest.class);

 /*   @BeforeClass
    public void setupToken() throws IOException {

        //setup(this.getClass().getSimpleName());
        logger.info("Starting....setupToken()");
        authToken = tokenUtil.getToken();
        logger.info("authToken..:"+authToken);

        logger.info("Setting up bearer token");
        bearerToken = "Bearer " + authToken; // Initialize bearerToken here
        logger.info("bearerToken..:"+bearerToken);

        logger.info("Loading Fixtures Templates from util packages ");
        FixtureFactoryLoader.loadTemplates("com.conduit.util");
    }*/

    /*private void initializeBearerToken() {
        bearerToken = "Bearer " + authToken; // Initialize bearerToken here
    }*/

    @Test(dataProvider = "updateArticleData",dataProviderClass = DataProvider.class)
    public void updateArticleTest(com.conduit.request.Pojo.Request.UpdateArticle.Article updateArticle) throws JsonProcessingException {
        updateArticleRequestPayload = new UpdateArticleRequestPayload();
        updateArticleRequestPayload.setArticle(updateArticle);

        objectMapper = new ObjectMapper();
        String updateArticleMainPayload = objectMapper.writeValueAsString(updateArticleRequestPayload);

        // Here "bearerToken" or AuthToken is inherited from APITEST BASE
        Response updateArticleResponsePayload = given().body(updateArticleMainPayload).when()
                .put("articles/Luke-5079").andReturn();
        System.out.println("updateArticleResponsePayload.asString() = " + updateArticleResponsePayload.body().asString());

        // Here new Article payresponse is used as update article and new article payload response json is same
        NewArticlePayloadResponse newArticlePayloadResponse = objectMapper.readValue(updateArticleResponsePayload.body().asString(), NewArticlePayloadResponse.class);
        //System.out.println("newArticlePayloadResponse.getArticle().getUpdatedAt() = " + newArticlePayloadResponse.getArticle().getUpdatedAt());
    }
}
