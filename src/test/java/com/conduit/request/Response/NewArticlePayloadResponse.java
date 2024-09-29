package com.conduit.request.Response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "article"
})

public class NewArticlePayloadResponse {

    @JsonProperty("article")
    private ArticleResponse article;

    @JsonProperty("article")
    public ArticleResponse getArticle() {
        return article;
    }

    @JsonProperty("article")
    public void setArticle(ArticleResponse article) {
        this.article = article;
    }

    public NewArticlePayloadResponse withArticle(ArticleResponse article) {
        this.article = article;
        return this;
    }

}