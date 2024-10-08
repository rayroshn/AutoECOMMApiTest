package com.conduit.request.ApiTest;

import com.conduit.request.ApiTestBase;
import com.conduit.request.Response.Article;
import com.conduit.request.Response.GetArticleResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetAllArticlesApiTest extends ApiTestBase {


    @Test
    public void getAllArticlesTest() {
        GetArticleResponse articlesResponse = given()
                .queryParam("limit", 300)  // Query parameter for limit
                .queryParam("offset", 0)  // Query parameter for offset
                .when()
                .get("/articles")  // API endpoint
                .then()
                .statusCode(200)  // Check if response status code is 200 (OK)
                .extract()
                .as(GetArticleResponse.class);  // Map the response to ArticlesResponse POJO
        System.out.println("articlesResponse.getArticlesCount() = " + articlesResponse.getArticlesCount());
        // Print the response data
        System.out.println("Total Articles Count: " + articlesResponse.getArticlesCount());
        for (Article article : articlesResponse.getArticles()) {
            System.out.println(article.getAuthor().getUsername());
            System.out.println(article.getTitle());
            System.out.println(article.getDescription());
            System.out.println(article.getSlug());
            System.out.println("========================");

        }
    }
}
