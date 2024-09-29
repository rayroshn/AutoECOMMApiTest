package com.Mock.Request.Pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "orderId",
        "products",
        "paymentOptions",
        "shippingAddresses"
})

public class CreateMockOrder {

    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("products")
    private List<Product> products;
    @JsonProperty("paymentOptions")
    private PaymentOptions paymentOptions;
    @JsonProperty("shippingAddresses")
    private List<ShippingAddress> shippingAddresses;

    @JsonProperty("orderId")
    public String getOrderId() {
        return orderId;
    }

    @JsonProperty("orderId")
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CreateMockOrder withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    @JsonProperty("products")
    public List<Product> getProducts() {
        return products;
    }

    @JsonProperty("products")
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public CreateMockOrder withProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    @JsonProperty("paymentOptions")
    public PaymentOptions getPaymentOptions() {
        return paymentOptions;
    }

    @JsonProperty("paymentOptions")
    public void setPaymentOptions(PaymentOptions paymentOptions) {
        this.paymentOptions = paymentOptions;
    }

    public CreateMockOrder withPaymentOptions(PaymentOptions paymentOptions) {
        this.paymentOptions = paymentOptions;
        return this;
    }

    @JsonProperty("shippingAddresses")
    public List<ShippingAddress> getShippingAddresses() {
        return shippingAddresses;
    }

    @JsonProperty("shippingAddresses")
    public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
    }

    public CreateMockOrder withShippingAddresses(List<ShippingAddress> shippingAddresses) {
        this.shippingAddresses = shippingAddresses;
        return this;
    }

}