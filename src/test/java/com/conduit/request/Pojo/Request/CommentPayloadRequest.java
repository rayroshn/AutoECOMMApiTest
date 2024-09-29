package com.conduit.request.Pojo.Request;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "comment"
})

public class CommentPayloadRequest {

    @JsonProperty("comment")
    private Comment comment;

    public CommentPayloadRequest(Comment comment) {
    }

    public CommentPayloadRequest() {

    }

    @JsonProperty("comment")
    public Comment getComment() {
        return comment;
    }

    @JsonProperty("comment")
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Comment withComment(Comment comment) {
        this.comment = comment;
        return comment;
    }

}