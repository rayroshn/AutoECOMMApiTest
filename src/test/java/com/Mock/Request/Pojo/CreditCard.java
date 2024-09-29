package com.Mock.Request.Pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "number",
        "expiry"
})

public class CreditCard {

    @JsonProperty("number")
    private String number;
    @JsonProperty("expiry")
    private String expiry;

    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    public CreditCard withNumber(String number) {
        this.number = number;
        return this;
    }

    @JsonProperty("expiry")
    public String getExpiry() {
        return expiry;
    }

    @JsonProperty("expiry")
    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public CreditCard withExpiry(String expiry) {
        this.expiry = expiry;
        return this;
    }

}
