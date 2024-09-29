package com.conduit.request.Response;


public class User {
    private String email;
    private String username;
    private String bio;  // Can be `null`, so use `String`
    private String image;
    private String token;

    // Constructors
    public User() {}

    public User(String email, String username, String bio, String image, String token) {
        this.email = email;
        this.username = username;
        this.bio = bio;
        this.image = image;
        this.token = token;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", image='" + image + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
