package com.conduit.request.Response.DeleteArtcile;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "article"
})

public class Errors {

    @JsonProperty("article")
    private List<String> article;

    @JsonProperty("article")
    public List<String> getArticle() {
        return article;
    }

    @JsonProperty("article")
    public void setArticle(List<String> article) {
        this.article = article;
    }

    public Errors withArticle(List<String> article) {
        this.article = article;
        return this;
    }
    @Override
    public String toString() {
        return "Errors{" +
                "article=" + article +
                '}';
    }
}