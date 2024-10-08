
package com.conduit.request.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "articles",
    "articlesCount"
})

public class GetArticleResponse {

    @JsonProperty("articles")
    private List<Article> articles;
    @JsonProperty("articlesCount")
    private Integer articlesCount;

    @JsonProperty("articles")
    public List<Article> getArticles() {
        return articles;
    }

    @JsonProperty("articles")
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public GetArticleResponse withArticles(List<Article> articles) {
        this.articles = articles;
        return this;
    }

    @JsonProperty("articlesCount")
    public Integer getArticlesCount() {
        return articlesCount;
    }

    @JsonProperty("articlesCount")
    public void setArticlesCount(Integer articlesCount) {
        this.articlesCount = articlesCount;
    }

    public GetArticleResponse withArticlesCount(Integer articlesCount) {
        this.articlesCount = articlesCount;
        return this;
    }

}
