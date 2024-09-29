package com.conduit.request.Pojo.Request;


import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "article"
})

public class NewArticlePayload {

    @JsonProperty("article")
    private Article article;

    public NewArticlePayload(Article article) {
        this.article = article;
    }

    public NewArticlePayload() {

    }

    @JsonProperty("article")
    public Article getArticle() {
        return article;
    }

    @JsonProperty("article")
    public void setArticle(Article article) {
        this.article = article;
    }

    public NewArticlePayload withArticle(Article article) {
        this.article = article;
        return this;
    }

}