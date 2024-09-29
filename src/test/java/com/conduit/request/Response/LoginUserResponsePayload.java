package com.conduit.request.Response;

public class LoginUserResponsePayload {
    private User user;

    // Constructors
    public LoginUserResponsePayload() {}

    public LoginUserResponsePayload(User user) {
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
