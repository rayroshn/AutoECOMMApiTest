package com.hv.response;

import java.util.List;
import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "message",
        "orders"
})

public class AddOrderPayloadResponse {

    @JsonProperty("message")
    private String message;
    @JsonProperty("orders")
    private List<Order> orders;

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public AddOrderPayloadResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("orders")
    public List<Order> getOrders() {
        return orders;
    }

    @JsonProperty("orders")
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public AddOrderPayloadResponse withOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }

}