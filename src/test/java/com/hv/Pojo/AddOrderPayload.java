package com.hv.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.hv.Pojo.SharedData.*;
//import static com.hv.Pojo.SharedData.expetecUserIds;

public class AddOrderPayload {


    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("product_name")
    private  String productName;

    @JsonProperty("product_amount")
    private String productAmount;  // Use double for amounts

    @JsonProperty("qty")
    private String qty;  // Use int for quantity

    @JsonProperty("tax_amt")
    private String taxAmt;

    @JsonProperty("total_amt")
    private String totalAmt;

    static Faker faker = new Faker();


    // Getter and Setter for userId
    public String getUserId() {
        return userId;
    }

    public AddOrderPayload setUserId(String userId) {
        this.userId = userId;
       return this;
        //return new AddOrderPayload();
    }

    // Getter and Setter for productId
    public String getProductId() {
        return productId;
    }

    public AddOrderPayload setProductId(String productId) {
        this.productId = productId;
       return this;
        //return new AddOrderPayload();
    }

    // Getter and Setter for productName
    public String getProductName() {
        return productName;
    }

    public AddOrderPayload setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    // Getter and Setter for productAmount
    public String getProductAmount() {
        return productAmount;
    }

    public AddOrderPayload setProductAmount(String productAmount) {
        this.productAmount = productAmount;
        return this;
    }

    // Getter and Setter for qty
   public String getQty() {
        return qty;
    }

    public AddOrderPayload setQty(String qty) {
        this.qty = qty;
        return this;
    }

    // Getter and Setter for taxAmt
    public String getTaxAmt() {
        return taxAmt;
    }

    public AddOrderPayload setTaxAmt(String taxAmt) {
        this.taxAmt = taxAmt;
        return this;
    }

    // Getter and Setter for totalAmt
    public String getTotalAmt() {
        return totalAmt;
    }

    public AddOrderPayload setTotalAmt(String totalAmt) {
        this.totalAmt = totalAmt;
        return this;
    }

    public static AddOrderPayload setOrderPayLoadNonBuilder() {
         List <String> productIdRange=Arrays.asList("2002", "2004", "2005", "2006");
        //productIdRange.get(new Random().nextInt(productIdRange.size()));
        // Set a random product ID and store it in expectedProductIds list
         String selectedProductId = productIdRange.get(new Random().nextInt(productIdRange.size()));
         String productNames = faker.commerce().productName();
         List <String> productAmountRange=Arrays.asList("8000", "10000", "15000", "20000");
         String productAmounts = productAmountRange.get(new Random().nextInt(productAmountRange.size()));
         String qtyy = String.valueOf(faker.number().numberBetween(1, 10)); // Generates a random quantity between 1 and 10
         String taxAmtt = String.valueOf(faker.number().numberBetween(1, 50)); // Generates a fake tax amount
         String totalAmount =String.valueOf(Integer.parseInt(productAmounts)+Integer.parseInt(taxAmtt));
         String userID=faker.idNumber().valid().replaceAll("-","").trim();

        expectedProductIds.add(selectedProductId);
        expetecUserIds.add(userID);
        expetecdProductName.add(productNames);
        expetecdproductamount.add(productAmounts);
        return new AddOrderPayload()
                .setUserId(userID)
                .setProductId(selectedProductId)
                .setProductName(productNames)
                .setProductAmount(productAmounts)
                .setQty(qtyy)
                .setTaxAmt(taxAmtt)
                .setTotalAmt(totalAmount);

    }
}
