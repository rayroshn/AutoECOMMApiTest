package com.conduit.request.Pojo.Request;

public class LoginUserPayload {
    private User user;

    // Constructors
    public LoginUserPayload() {
    }

    public LoginUserPayload(User user) {
        this.user = user;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserWrapper{" +
                "user=" + user +
                '}';
    }
}
