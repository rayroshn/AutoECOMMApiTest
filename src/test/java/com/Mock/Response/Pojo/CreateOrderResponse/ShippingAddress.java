package com.Mock.Response.Pojo.CreateOrderResponse;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "line1",
        "city",
        "state",
        "postalCode",
        "country"
})

public class ShippingAddress {

    @JsonProperty("line1")
    private String line1;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("postalCode")
    private String postalCode;
    @JsonProperty("country")
    private String country;

    @JsonProperty("line1")
    public String getLine1() {
        return line1;
    }

    @JsonProperty("line1")
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public ShippingAddress withLine1(String line1) {
        this.line1 = line1;
        return this;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    public ShippingAddress withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    public ShippingAddress withState(String state) {
        this.state = state;
        return this;
    }

    @JsonProperty("postalCode")
    public String getPostalCode() {
        return postalCode;
    }

    @JsonProperty("postalCode")
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public ShippingAddress withPostalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    public ShippingAddress withCountry(String country) {
        this.country = country;
        return this;
    }

}