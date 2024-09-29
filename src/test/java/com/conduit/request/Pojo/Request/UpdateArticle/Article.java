package com.conduit.request.Pojo.Request.UpdateArticle;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "body"
})

public class Article {

    @JsonProperty("body")
    private String body;

    @JsonProperty("body")
    public String getBody() {
        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {
        this.body = body;
    }

    public Article withBody(String body) {
        this.body = body;
        return this;
    }

}