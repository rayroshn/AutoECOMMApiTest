package com.conduit.request.Response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "createdAt",
        "updatedAt",
        "body",
        "author"
})

public class CommentResponse {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("body")
    private String body;
    @JsonProperty("author")
    private Author author;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public CommentResponse withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public CommentResponse withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public CommentResponse withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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

    public CommentResponse withBody(String body) {
        this.body = body;
        return this;
    }

    @JsonProperty("author")
    public Author getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author author) {
        this.author = author;
    }

    public CommentResponse withAuthor(Author author) {
        this.author = author;
        return this;
    }

}
