package com.conduit.request.Pojo.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CommentFactory {

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

    public CommentFactory withBody(String body) {
        this.body = body;
        return this;
    }

}
