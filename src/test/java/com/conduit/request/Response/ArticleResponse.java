package com.conduit.request.Response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "slug",
        "title",
        "description",
        "body",
        "tagList",
        "createdAt",
        "updatedAt",
        "favorited",
        "favoritesCount",
        "author"
})

public class ArticleResponse {

    @JsonProperty("slug")
    private String slug;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("body")
    private String body;
    @JsonProperty("tagList")
    private List<String> tagList;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("favorited")
    private Boolean favorited;
    @JsonProperty("favoritesCount")
    private Integer favoritesCount;
    @JsonProperty("author")
    private Author author;

    @JsonProperty("slug")
    public String getSlug() {
        return slug;
    }

    @JsonProperty("slug")
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public ArticleResponse withSlug(String slug) {
        this.slug = slug;
        return this;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    public ArticleResponse withTitle(String title) {
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

    public ArticleResponse withDescription(String description) {
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

    public ArticleResponse withBody(String body) {
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

    public ArticleResponse withTagList(List<String> tagList) {
        this.tagList = tagList;
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

    public ArticleResponse withCreatedAt(String createdAt) {
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

    public ArticleResponse withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @JsonProperty("favorited")
    public Boolean getFavorited() {
        return favorited;
    }

    @JsonProperty("favorited")
    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public ArticleResponse withFavorited(Boolean favorited) {
        this.favorited = favorited;
        return this;
    }

    @JsonProperty("favoritesCount")
    public Integer getFavoritesCount() {
        return favoritesCount;
    }

    @JsonProperty("favoritesCount")
    public void setFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
    }

    public ArticleResponse withFavoritesCount(Integer favoritesCount) {
        this.favoritesCount = favoritesCount;
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

    public ArticleResponse withAuthor(Author author) {
        this.author = author;
        return this;
    }

}
