package com.conduit.request.Pojo.Request.UpdateArticle;

import com.conduit.request.Pojo.Request.Article;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "article"
})

public class UpdateArticleRequestPayload {

    @JsonProperty("article")
    private com.conduit.request.Pojo.Request.UpdateArticle.Article article;

    @JsonProperty("article")
    public com.conduit.request.Pojo.Request.UpdateArticle.Article getArticle() {
        return article;
    }

    @JsonProperty("article")
    public void setArticle(com.conduit.request.Pojo.Request.UpdateArticle.Article article) {
        this.article = article;
    }

    public UpdateArticleRequestPayload withArticle(com.conduit.request.Pojo.Request.UpdateArticle.Article article) {
        this.article = article;
        return this;
    }

    /*public void setArticle(com.conduit.request.Pojo.Request.UpdateArticle.Article updateArticle) {
        this.article = updateArticle;
    }*/
}