package com.conduit.util.FixturesTemplates;

import com.conduit.request.Pojo.Request.Product;
import org.testng.annotations.DataProvider;

public class ProductDataProvider {

    @DataProvider(name = "getProductTemplate")
    public static Object[][] getProductTemplate() {
        Product product = new Product();
        product.setId(0);
        product.setTitle("string");
        product.setPrice(0.1);
        product.setDescription("string");
        product.setCategory("string");
        product.setImage("http://example.com");

        return new Object[][]{{product}};
    }
}
