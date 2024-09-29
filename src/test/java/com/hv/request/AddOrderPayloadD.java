package com.hv.request;


public class AddOrderPayloadD {
    private String userId;
    private String productId;
    private String productName;
    private String productAmount;
    private String qty;
    private String taxAmt;
    private String totalAmt;

    public AddOrderPayloadD()
    {


    }
    // Getter and Setter for userId
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and Setter for productId
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    // Getter and Setter for productName
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter and Setter for productAmount
    public String getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(String productAmount) {
        this.productAmount = productAmount;
    }

    // Getter and Setter for qty
    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    // Getter and Setter for taxAmt
    public String getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(String taxAmt) {
        this.taxAmt = taxAmt;
    }

    // Getter and Setter for totalAmt
    public String getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
    }






/*
    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AddOrderPayload withUserId(String userId) {
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

    public AddOrderPayload withProductId(String productId) {
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

    public AddOrderPayload withProductName(String productName) {
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

    public AddOrderPayload withProductAmount(String productAmount) {
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

    public AddOrderPayload withQty(String qty) {
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

    public AddOrderPayload withTaxAmt(String taxAmt) {
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

    public AddOrderPayload withTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
        return this;
    }*/

}