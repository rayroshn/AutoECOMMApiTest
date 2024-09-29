package com.Mock.Request.Pojo;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "productId",
        "quantity",
        "coupons",
        "category"
})

public class Product {

    @JsonProperty("productId")
    private String productId;
    @JsonProperty("quantity")
    private Integer quantity;
    @JsonProperty("coupons")
    private List<String> coupons;
    @JsonProperty("category")
    private String category;

    @JsonProperty("productId")
    public String getProductId() {
        return productId;
    }

    @JsonProperty("productId")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Product withProductId(String productId) {
        this.productId = productId;
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

    public Product withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @JsonProperty("coupons")
    public List<String> getCoupons() {
        return coupons;
    }

    @JsonProperty("coupons")
    public void setCoupons(List<String> coupons) {
        this.coupons = coupons;
    }

    public Product withCoupons(List<String> coupons) {
        this.coupons = coupons;
        return this;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    public Product withCategory(String category) {
        this.category = category;
        return this;
    }

}