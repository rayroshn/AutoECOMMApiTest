package com.conduit.request.Pojo.Request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "body"
})

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

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

    public Comment withBody(String body) {
        this.body = body;
        return this;
    }

}
