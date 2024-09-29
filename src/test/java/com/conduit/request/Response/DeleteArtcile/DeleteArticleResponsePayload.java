package com.conduit.request.Response.DeleteArtcile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "errors"
})
    public class DeleteArticleResponsePayload {

        @JsonProperty("errors")
        private Errors errors;

        @JsonProperty("errors")
        public Errors getErrors() {
            return errors;
        }

        @JsonProperty("errors")
        public void setErrors(Errors errors) {
            this.errors = errors;
        }

        public DeleteArticleResponsePayload withErrors(Errors errors) {
            this.errors = errors;
            return this;
        }

    }
