package com.conduit.request.Response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "username",
        "bio",
        "image",
        "following"
})

public class Author {

    @JsonProperty("username")
    private String username;
    @JsonProperty("bio")
    private Object bio;
    @JsonProperty("image")
    private String image;
    @JsonProperty("following")
    private Boolean following;

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public Author withUsername(String username) {
        this.username = username;
        return this;
    }

    @JsonProperty("bio")
    public Object getBio() {
        return bio;
    }

    @JsonProperty("bio")
    public void setBio(Object bio) {
        this.bio = bio;
    }

    public Author withBio(Object bio) {
        this.bio = bio;
        return this;
    }

    @JsonProperty("image")
    public String getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(String image) {
        this.image = image;
    }

    public Author withImage(String image) {
        this.image = image;
        return this;
    }

    @JsonProperty("following")
    public Boolean getFollowing() {
        return following;
    }

    @JsonProperty("following")
    public void setFollowing(Boolean following) {
        this.following = following;
    }

    public Author withFollowing(Boolean following) {
        this.following = following;
        return this;
    }

}