package com.Mock.Response.Pojo.CreateOrderResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "method",
        "total"
})

public class Payment {

    @JsonProperty("method")
    private String method;
    @JsonProperty("total")
    private Float total;

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    public Payment withMethod(String method) {
        this.method = method;
        return this;
    }

    @JsonProperty("total")
    public Float getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Float total) {
        this.total = total;
    }

    public Payment withTotal(Float total) {
        this.total = total;
        return this;
    }

}
