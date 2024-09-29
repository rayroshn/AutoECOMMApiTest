package com.Mock.Request.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "street",
        "city",
        "zip",
        "contactNumber"
})

public class ShippingAddress {

    @JsonProperty("street")
    private String street;
    @JsonProperty("city")
    private String city;
    @JsonProperty("zip")
    private String zip;
    @JsonProperty("contactNumber")
    private String contactNumber;

    @JsonProperty("street")
    public String getStreet() {
        return street;
    }

    @JsonProperty("street")
    public void setStreet(String street) {
        this.street = street;
    }

    public ShippingAddress withStreet(String street) {
        this.street = street;
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

    @JsonProperty("zip")
    public String getZip() {
        return zip;
    }

    @JsonProperty("zip")
    public void setZip(String zip) {
        this.zip = zip;
    }

    public ShippingAddress withZip(String zip) {
        this.zip = zip;
        return this;
    }

    @JsonProperty("contactNumber")
    public String getContactNumber() {
        return contactNumber;
    }

    @JsonProperty("contactNumber")
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public ShippingAddress withContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

}