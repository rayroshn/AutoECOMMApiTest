package com.conduit.request.ApiTest;

import com.conduit.Constants.FilePaths;
import com.conduit.request.ApiTestBase;
import com.conduit.request.Pojo.Request.Article;
import com.conduit.request.Pojo.Request.NewArticlePayload;
import com.conduit.request.Response.NewArticlePayloadResponse;
import com.conduit.util.CsvReaderUtill;
import com.conduit.util.FakerData.ArticleFakerData;
import com.conduit.util.FakerData.FakerDataUtil;
import com.conduit.util.FixturesTemplates.DataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class articleAPITest extends ApiTestBase {

    Response response;
    ObjectMapper objectMapper;
    Faker faker = new Faker();
    String title=faker.name().title();
    List<String> description= FakerDataUtil.GET_DESCRIPTION_DATA;
        String userCsvFilePath= FilePaths.LOGIN_USER_DATA.getPath();


    /*@BeforeClass
    public void setupToken() throws IOException {

        //setup(this.getClass().getSimpleName());
        logger.info("Starting.... SetupToken");
        authToken = tokenUtil.getToken();

        logger.info("Loading Fixtures Templates from util packages ");
        FixtureFactoryLoader.loadTemplates("com.conduit.util");
    }*/

    @Step("This is a test step")
    @Test
    public void createNewArticle() throws IOException {
        Article article = new Article();
        article.setDescription(description.toString());
        article.setTitle(title);
        article.setBody("API body");
        article.setTagList(Arrays.asList("esse","voluptatem"));
        NewArticlePayload newArticlePayload = new NewArticlePayload(article);

        // ObjectMapper for JSON to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        final String articleMainPayload = objectMapper.writeValueAsString(newArticlePayload);
        response = given().body(articleMainPayload)
                .when().post("/articles").andReturn();

        assertEquals(String.valueOf(response.statusCode()),"201");

        NewArticlePayloadResponse newArticlePayloadResponse = objectMapper.readValue(response.getBody().asString(), NewArticlePayloadResponse.class);
        String articleDescription = newArticlePayloadResponse.getArticle().getDescription().toString();
        assertEquals(articleDescription,description.toString());
        //Assert.assertEquals(newArticlePayloadResponse.getArticle().getTagList(),Arrays.asList("esse","voluptatem"));


        //Assert TagList
        List<String> actualTaglist =newArticlePayloadResponse.getArticle().getTagList();
        List<String>  expectedTagList= Arrays.asList("esse","voluptatem");
        //Normal For loop
              /*for(int i=0;i<expectedTagList.size();i++)
              {assertEquals(actualTaglist.get(i),expectedTagList.get(i)); }*/
              // Java 8 stream
        IntStream.range(0,expectedTagList.size()).forEach(i -> assertEquals(actualTaglist.get(i),expectedTagList.get(i)));

        //Assert Author name
        assertEquals(newArticlePayloadResponse.getArticle().getAuthor().getUsername(),CsvReaderUtill.readCsvData(userCsvFilePath).get(0).get("username"));



    }

    @Test(dataProvider = "getArticleTemplate",dataProviderClass = DataProvider.class)
    public void createNewArticleViaTemplate(Article article) throws IOException {
        NewArticlePayload newArticlePayload = new NewArticlePayload();
        newArticlePayload.setArticle(article);

        // ObjectMapper for JSON to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        final String articleMainPayload = objectMapper.writeValueAsString(newArticlePayload);
        response = given().body(articleMainPayload)
                .when().post("/articles").andReturn();

        assertEquals(String.valueOf(response.statusCode()),"201");

        NewArticlePayloadResponse newArticlePayloadResponse = objectMapper.readValue(response.getBody().asString(), NewArticlePayloadResponse.class);
        String articleDescription = newArticlePayloadResponse.getArticle().getDescription().toString();
        assertEquals(articleDescription, ArticleFakerData.GET_ARTICLE_DESC_DATA);
        //Assert.assertEquals(newArticlePayloadResponse.getArticle().getTagList(),Arrays.asList("esse","voluptatem"));


        //Assert TagList
        List<String> actualTaglist =newArticlePayloadResponse.getArticle().getTagList();
        List<String>  expectedTagList= Arrays.asList("esse","voluptatem");
        //Normal For loop
              /*for(int i=0;i<expectedTagList.size();i++)
              {assertEquals(actualTaglist.get(i),expectedTagList.get(i)); }*/
        // Java 8 stream
        IntStream.range(0,expectedTagList.size()).forEach(i -> assertEquals(actualTaglist.get(i),expectedTagList.get(i)));

        //Assert Author name
        assertEquals(newArticlePayloadResponse.getArticle().getAuthor().getUsername(),CsvReaderUtill.readCsvData(userCsvFilePath).get(0).get("username"));



    }

    @Test
    public void createNewArticleRequestSchemaValidation() throws IOException
    {
        Article article = new Article();
        article.setDescription(description.toString());
        article.setTitle(title);
        article.setBody("API body");
        article.setTagList(Arrays.asList("esse","voluptatem"));
        NewArticlePayload newArticlePayload = new NewArticlePayload(article);

        // Convert the POJO to JSON
        objectMapper = new ObjectMapper();
        String createNewArticleRequestPayload = objectMapper.writeValueAsString(newArticlePayload);

        InputStream createNewArticleRequestSchema = getClass().getClassLoader().getResourceAsStream("Article_SchemaRequest.json");
        JSONObject rawSchema= new JSONObject(new String(createNewArticleRequestSchema.readAllBytes()));
        Schema schema = SchemaLoader.load(rawSchema);
        JSONObject jsonObject = new JSONObject(createNewArticleRequestPayload);
        schema.validate(jsonObject);


    }


    @Test
    public void createNewArticleRequestNegativeSchemaValidation() throws IOException
    {
        Article article = new Article();
        article.setDescription(description.toString());
            article.setTitle(title);
        article.setBody("API body");
        article.setTagList(Arrays.asList("esse","voluptatem"));
        NewArticlePayload newArticlePayload = new NewArticlePayload(article);

        // Convert the POJO to JSON
        objectMapper = new ObjectMapper();
        String createNewArticleRequestPayload = objectMapper.writeValueAsString(newArticlePayload);

        //Invalid check
        String createInvalidArticleRequestPayload = createNewArticleRequestPayload.replace("\"title\":\"" + title + "\"", "\"title\":123");

        InputStream createNewArticleRequestSchema = getClass().getClassLoader().getResourceAsStream("Article_SchemaRequest.json");
        JSONObject rawSchema= new JSONObject(new String(createNewArticleRequestSchema.readAllBytes()));
        Schema schema = SchemaLoader.load(rawSchema);
        JSONObject jsonObject = new JSONObject(createInvalidArticleRequestPayload);
       // schema.validate(jsonObject);

        try {
            schema.validate(jsonObject);
            // If no exception is thrown, fail the test
            Assert.fail("Expected ValidationException was not thrown.");
        } catch (ValidationException validationException) {
            // Assert on the exception message
            Assert.assertTrue(validationException.getMessage().contains("expected type: String, found: Integer"),
                    "Expected validation error message not found.");
            System.out.println("Schema validation failed as expected: " + validationException.getMessage());
        }
        // Optionally, assert on the exception message
       /* Assert.assertTrue(validationException.getMessage().contains("expected type: String, found: Integer"),
                "Expected validation error message not found.");*/


    }

    @Test(dataProvider = "getArticleTemplate",dataProviderClass = DataProvider.class)
    public void createNewArticleResponseSchemaValidation(Article article) throws IOException {

        InputStream createNewArticleResponseSchema=getClass().getClassLoader().getResourceAsStream("Article_SchemaResponse.json");

      /*  Article article = new Article();
        article.setDescription(description.toString());
        article.setTitle(title);
        article.setBody("API body");
        article.setTagList(Arrays.asList("esse","voluptatem"));
        NewArticlePayload newArticlePayload = new NewArticlePayload(article);*/
        NewArticlePayload newArticlePayload = new NewArticlePayload();
        newArticlePayload.setArticle(article);

        // ObjectMapper for JSON to POJO
        ObjectMapper objectMapper = new ObjectMapper();
        final String articleMainPayload = objectMapper.writeValueAsString(newArticlePayload);
        ValidatableResponse validatableResponse = given().body(articleMainPayload)
                .when().post("/articles").then().assertThat().statusCode(201).
                        body(JsonSchemaValidator.matchesJsonSchema(createNewArticleResponseSchema));


        logger.debug("validatableResponse >>>>>>  "+validatableResponse);


       /* assertEquals(String.valueOf(response.statusCode()),"201");

        NewArticlePayloadResponse newArticlePayloadResponse = objectMapper.readValue(response.getBody().asString(), NewArticlePayloadResponse.class);
        String articleDescription = newArticlePayloadResponse.getArticle().getDescription().toString();
        assertEquals(articleDescription, ArticleFakerData.GET_ARTICLE_DESC_DATA);*/
        //Assert.assertEquals(newArticlePayloadResponse.getArticle().getTagList(),Arrays.asList("esse","voluptatem"));


        //Assert TagList
       /* List<String> actualTaglist =newArticlePayloadResponse.getArticle().getTagList();
        List<String>  expectedTagList= Arrays.asList("esse","voluptatem");
        //Normal For loop
              *//*for(int i=0;i<expectedTagList.size();i++)
              {assertEquals(actualTaglist.get(i),expectedTagList.get(i)); }*//*
        // Java 8 stream
        IntStream.range(0,expectedTagList.size()).forEach(i -> assertEquals(actualTaglist.get(i),expectedTagList.get(i)));

        //Assert Author name
        assertEquals(newArticlePayloadResponse.getArticle().getAuthor().getUsername(),CsvReaderUtill.readCsvData(userCsvFilePath).get(0).get("username"));

*/

    }

    @Test(dataProvider = "getArticleTemplate",dataProviderClass = DataProvider.class)
    public void createNewArticleResponseNegativeSchemaValidation(Article article) throws IOException {
        logger.info("Schema validation for createNewArticleResponseNegativeSchemaValidation test case..");
        InputStream createNewArticleResponseSchema=getClass().getClassLoader().getResourceAsStream("Article_SchemaResponse.json");

        NewArticlePayload newArticlePayload = new NewArticlePayload();
        newArticlePayload.setArticle(article);

        // ObjectMapper for JSON to POJO
         objectMapper = new ObjectMapper();
         String articleMainPayload = objectMapper.writeValueAsString(newArticlePayload);
        logger.debug("Original Payload: {}", articleMainPayload);

         String articleMainPayloadInvalid = articleMainPayload.replace("\"title\":\"" + ArticleFakerData.GET_ARTICLE_TITLE_DATA + "\"", "\"title\": 123");
        logger.debug("Modified Payload: {}", articleMainPayload);

        ValidatableResponse validatableResponse = given().body(articleMainPayload)
                .when().post("/articles").then().assertThat().statusCode(201).
                        body(JsonSchemaValidator.matchesJsonSchema(createNewArticleResponseSchema));
       logger.debug("validatableResponse>>>>>>>>:: " +validatableResponse);
        logger.info("Schema validation completed.");

    }
}
