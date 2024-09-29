package com.Mock.Response.Pojo.CreateOrderResponse;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "message",
        "orderId",
        "orderDetails",
        "confirmationNumber",
        "estimatedDelivery"
})

public class CreateOrderResponse {

    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("orderDetails")
    private OrderDetails orderDetails;
    @JsonProperty("confirmationNumber")
    private String confirmationNumber;
    @JsonProperty("estimatedDelivery")
    private String estimatedDelivery;

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    public CreateOrderResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public CreateOrderResponse withMessage(String message) {
        this.message = message;
        return this;
    }

    @JsonProperty("orderId")
    public String getOrderId() {
        return orderId;
    }

    @JsonProperty("orderId")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CreateOrderResponse withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    @JsonProperty("orderDetails")
    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    @JsonProperty("orderDetails")
    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public CreateOrderResponse withOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
        return this;
    }

    @JsonProperty("confirmationNumber")
    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    @JsonProperty("confirmationNumber")
    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public CreateOrderResponse withConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
        return this;
    }

    @JsonProperty("estimatedDelivery")
    public String getEstimatedDelivery() {
        return estimatedDelivery;
    }

    @JsonProperty("estimatedDelivery")
    public void setEstimatedDelivery(String estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    public CreateOrderResponse withEstimatedDelivery(String estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
        return this;
    }

}