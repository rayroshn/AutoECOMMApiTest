package com.conduit.request.Response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "comment"
})


public class CommentPayloadResponse {

    @JsonProperty("comment")
    private CommentResponse comment;

    @JsonProperty("comment")
    public CommentResponse getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(CommentResponse comment) {
        this.comment = comment;
    }

    public CommentPayloadResponse withComment(CommentResponse comment) {
        this.comment = comment;
        return this;
    }

}

