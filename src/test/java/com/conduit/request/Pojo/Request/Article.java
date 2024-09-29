package com.conduit.request.Pojo.Request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "description",
        "body",
        "tagList"
})

public class Article {

    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("body")
    private String body;
    @JsonProperty("tagList")
    private List<String> tagList;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public Article withTitle(String title) {
        this.title = title;
        return this;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Article withDescription(String description) {
        this.description = description;
        return this;
    }

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

    @JsonProperty("tagList")
    public List<String> getTagList() {
        return tagList;
    }

    @JsonProperty("tagList")
    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public Article withTagList(List<String> tagList) {
        this.tagList = tagList;
        return this;
    }

}