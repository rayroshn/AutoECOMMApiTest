
package com.conduit.request.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "slug",
    "title",
    "description",
    "tagList",
    "createdAt",
    "favorited",
    "favoritesCount",
    "author"
})

public class Article {

    @JsonProperty("slug")
    private String slug;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("tagList")
    private List<String> tagList;
    @JsonProperty("createdAt")
    private String createdAt;
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

    public Article withSlug(String slug) {
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

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Article withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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

    public Article withFavorited(Boolean favorited) {
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

    public Article withFavoritesCount(Integer favoritesCount) {
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

    public Article withAuthor(Author author) {
        this.author = author;
        return this;
    }

}
