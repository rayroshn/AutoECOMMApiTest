package com.Mock.ApiTest;

import com.Mock.BaseTest;
import com.Mock.Request.Pojo.*;
import com.Mock.Response.Pojo.CreateOrderResponse.CreateOrderResponse;
import com.Mock.Response.Pojo.CreateOrderResponse.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class CreateOrderApiTest extends BaseTest {



    @Test
    public void createNewOrderApiTest() throws IOException {
        CreateMockOrder createMockOrder = new CreateMockOrder();
        CreditCard creditCard = new CreditCard();
        PaymentOptions paymentOptions = new PaymentOptions();
        Paypal paypal = new Paypal();
        Product product = new Product();
        ShippingAddress shippingAddress = new ShippingAddress();

        createMockOrder.setOrderId("123456");

        product.setProductId("abc123");
        product.setCategory("Electronics");
        product.setCoupons(Arrays.asList("SAVE10","FREESHIP"));
        product.setQuantity(2);
        createMockOrder.setProducts(Arrays.asList(product));

        creditCard.setNumber("4111111111111111");
        creditCard.setExpiry("12/25");
        paypal.setEmail("user@example.com");

        paymentOptions.setCreditCard(creditCard);
        paymentOptions.setPaypal(paypal);
        createMockOrder.setPaymentOptions(paymentOptions);

        shippingAddress.setCity("Anytown");
        shippingAddress.setContactNumber("123-456-7890");
        shippingAddress.setStreet("123 Main St");
        shippingAddress.setZip("12345");
        createMockOrder.setShippingAddresses(List.of(shippingAddress));

        ObjectMapper objectMapper = new ObjectMapper();
        String createOrderPayload = objectMapper.writeValueAsString(createMockOrder);

        Response response = given().body(createOrderPayload).when().post("").andReturn();

        CreateOrderResponse createOrderResponse = objectMapper.readValue(response.asString(), CreateOrderResponse.class);

        String city = createOrderResponse.getOrderDetails().getBillingAddress().getCity();
        System.out.println("city = " + city);

        System.out.println("createOrderResponse.getMessage() = " + createOrderResponse.getMessage());
        System.out.println("createOrderResponse.getOrderDetails().getPayment().getTotal() = " + createOrderResponse.getOrderDetails().getPayment().getTotal());
        List<String> items = createOrderResponse.getOrderDetails().getItems().stream().map(m -> m.getProductName()).collect(Collectors.toList());
      //  items.stream().forEach(s-> System.out.println("s.getProductId()+ \"\" +s.getProductName() + \"\" + s.getPrice() = " + s.getProductId()+ "" +s.getProductName() + "" + s.getPrice()));
        List<String> expectedItems = List.of("Smartphone","Laptop");
        expectedItems.forEach( i -> {
            Assert.assertTrue(items.contains(i),"Expected Items " + i +"  not listed :: " );

        });

        Assert.assertEquals(response.statusCode(),201);

        System.out.println("response = " + response.asString());


    }

}