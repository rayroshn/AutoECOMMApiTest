package com.hv.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
public class AddOrderPayloadBuilderPatternLombok {
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



    public static AddOrderPayloadBuilderPatternLombok setOrderDataLombok()
    {

        /*return AddOrderPayloadBuilderPatternLombok.builder().
                productId("2020").productAmount("4000").productName("OPPA").userId("8080").
                totalAmt("8080").taxAmt("80").qty("1").build();*/
        return AddOrderPayloadBuilderPatternLombok.builder()
                .userId("8080")
                .productId("2020")
                .productName("OPPA")
                .productAmount("4000")
                .qty("1")
                .taxAmt("80")
                .totalAmt("4080")
                .build();

    }
    // Private constructor to be accessed via the Builder
   /* private AddOrderPayloadBuilderPatternLombok(Builder builder) {
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

        public AddOrderPayloadBuilderPatternLombok build() {
            return new AddOrderPayloadBuilderPatternLombok(this);
        }
    }*/
}
