package com.hv.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddOrderPayloadBuilderPattern {
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

    // Private constructor to be accessed via the Builder
    private AddOrderPayloadBuilderPattern(Builder builder) {
        this.userId = builder.userId;
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.productAmount = builder.productAmount;
        this.qty = builder.qty;
        this.taxAmt = builder.taxAmt;
        this.totalAmt = builder.totalAmt;
    }

    // Get values
    // Getter methods
    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductAmount() {
        return productAmount;
    }

    public String getQty() {
        return qty;
    }

    public String getTaxAmt() {
        return taxAmt;
    }

    public String getTotalAmt() {
        return totalAmt;
    }

    public static AddOrderPayloadBuilderPattern setOrderDataFromBuilder()
    {
       return new AddOrderPayloadBuilderPattern.Builder()
                .setUserId("10010")
                .setProductId("20010")
                .setProductName("LaptopProX")
                .setProductAmount("1200")
                .setQty("3")
                .setTaxAmt("36")
                .setTotalAmt("3636")
                .build();
    }

    // Static Builder class
    public static class Builder {
        private String userId;
        private String productId;
        private String productName;
        private String productAmount;
        private String qty;
        private String taxAmt;
        private String totalAmt;

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setProductId(String productId) {
            this.productId = productId;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setProductAmount(String productAmount) {
            this.productAmount = productAmount;
            return this;
        }

        public Builder setQty(String qty) {
            this.qty = qty;
            return this;
        }

        public Builder setTaxAmt(String taxAmt) {
            this.taxAmt = taxAmt;
            return this;
        }

        public Builder setTotalAmt(String totalAmt) {
            this.totalAmt = totalAmt;
            return this;
        }

        public AddOrderPayloadBuilderPattern build() {
            return new AddOrderPayloadBuilderPattern(this);
        }
    }
}
