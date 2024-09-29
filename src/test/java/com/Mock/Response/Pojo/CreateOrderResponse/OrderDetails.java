package com.Mock.Response.Pojo.CreateOrderResponse;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "customer",
        "shippingAddress",
        "billingAddress",
        "items",
        "payment"
})

public class OrderDetails {

    @JsonProperty("customer")
    private Customer customer;
    @JsonProperty("shippingAddress")
    private ShippingAddress shippingAddress;
    @JsonProperty("billingAddress")
    private BillingAddress billingAddress;
    @JsonProperty("items")
    private List<Item> items;
    @JsonProperty("payment")
    private Payment payment;

    @JsonProperty("customer")
    public Customer getCustomer() {
        return customer;
    }

    @JsonProperty("customer")
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderDetails withCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @JsonProperty("shippingAddress")
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    @JsonProperty("shippingAddress")
    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public OrderDetails withShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    @JsonProperty("billingAddress")
    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    @JsonProperty("billingAddress")
    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public OrderDetails withBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public OrderDetails withItems(List<Item> items) {
        this.items = items;
        return this;
    }

    @JsonProperty("payment")
    public Payment getPayment() {
        return payment;
    }

    @JsonProperty("payment")
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public OrderDetails withPayment(Payment payment) {
        this.payment = payment;
        return this;
    }

}