package com.Mock.Request.Pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "creditCard",
        "paypal"
})

public class PaymentOptions {

    @JsonProperty("creditCard")
    private CreditCard creditCard;
    @JsonProperty("paypal")
    private Paypal paypal;

    @JsonProperty("creditCard")
    public CreditCard getCreditCard() {
        return creditCard;
    }

    @JsonProperty("creditCard")
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public PaymentOptions withCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    @JsonProperty("paypal")
    public Paypal getPaypal() {
        return paypal;
    }

    @JsonProperty("paypal")
    public void setPaypal(Paypal paypal) {
        this.paypal = paypal;
    }

    public PaymentOptions withPaypal(Paypal paypal) {
        this.paypal = paypal;
        return this;
    }

}