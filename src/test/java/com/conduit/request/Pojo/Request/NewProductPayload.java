package com.conduit.request.Pojo.Request;

public class NewProductPayload {
    private Product product;

    public NewProductPayload() {}

    public NewProductPayload(Product product) { this.product = product; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
