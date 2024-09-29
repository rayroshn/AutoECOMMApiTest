package com.Mock.Response.Pojo.CreateOrderResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "productId",
        "productName",
        "quantity",
        "price"
})

public class Item {

    @JsonProperty("productId")
    private String productId;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("price")
    private Float price;

    @JsonProperty("productId")
    public String getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Item withProductId(String productId) {
        this.productId = productId;
        return this;
    }

    @JsonProperty("productName")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("productName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Item withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Item withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @JsonProperty("price")
    public Float getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Float price) {
        this.price = price;
    }

    public Item withPrice(Float price) {
        this.price = price;
        return this;
    }

}