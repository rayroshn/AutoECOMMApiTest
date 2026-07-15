package com.conduit.request.Response;

import com.conduit.request.Pojo.Request.Product;

public class NewProductPayloadResponse {
    private Product article; // reuse field name 'article' to keep structure similar

    public NewProductPayloadResponse() {}

    public Product getArticle() { return article; }
    public void setArticle(Product article) { this.article = article; }
}
