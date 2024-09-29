package com.hv.response;


import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "user_id",
        "product_id",
        "product_name",
        "product_amount",
        "qty",
        "tax_amt",
        "total_amt"
})

public class Order {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("product_id")
    private String productId;
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_amount")
    private String productAmount;
    @JsonProperty("qty")
    private String qty;
    @JsonProperty("tax_amt")
    private String taxAmt;
    @JsonProperty("total_amt")
    private String totalAmt;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Order withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Order withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @JsonProperty("product_id")
    public String getProductId() {
        return productId;
    }

    @JsonProperty("product_id")
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Order withProductId(String productId) {
        this.productId = productId;
        return this;
    }

    @JsonProperty("product_name")
    public String getProductName() {
        return productName;
    }

    @JsonProperty("product_name")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Order withProductName(String productName) {
        this.productName = productName;
        return this;
    }

    @JsonProperty("product_amount")
    public String getProductAmount() {
        return productAmount;
    }

    @JsonProperty("product_amount")
    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    public Order withProductAmount(String productAmount) {
        this.productAmount = productAmount;
        return this;
    }

    @JsonProperty("qty")
    public String getQty() {
        return qty;
    }

    @JsonProperty("qty")
    public void setQty(String qty) {
        this.qty = qty;
    }

    public Order withQty(String qty) {
        this.qty = qty;
        return this;
    }

    @JsonProperty("tax_amt")
    public String getTaxAmt() {
        return taxAmt;
    }

    @JsonProperty("tax_amt")
    public void setTaxAmt(String taxAmt) {
        this.taxAmt = taxAmt;
    }

    public Order withTaxAmt(String taxAmt) {
        this.taxAmt = taxAmt;
        return this;
    }

    @JsonProperty("total_amt")
    public String getTotalAmt() {
        return totalAmt;
    }

    @JsonProperty("total_amt")
    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Order withTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
        return this;
    }

}