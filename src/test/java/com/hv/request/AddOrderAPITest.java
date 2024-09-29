package com.hv.request;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.hv.Pojo.AddOrderPayload;
import com.hv.Pojo.AddOrderPayloadBuilderPattern;
import com.hv.Pojo.AddOrderPayloadBuilderPatternLombok;
import com.hv.response.AddOrderPayloadResponse;
import com.hv.response.Order;
import groovyjarjarantlr4.runtime.debug.RemoteDebugEventSocketListener;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.codehaus.groovy.control.XStreamUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hv.Pojo.SharedData.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasLength;

public class AddOrderAPITest extends BaseTest {

    // AddOrderPayload order = AddOrderPayload.setOrderPayLoadNonBuilder();
    // Create a new list to store AddOrderPayload objects
    List<AddOrderPayload> multipleAddorder = new ArrayList<>();
    ObjectMapper ordMap = new ObjectMapper();

    public AddOrderAPITest() throws JsonProcessingException {
    }

    @Test()
    public void testAddOrder() throws IOException {
        // Non Builder()

        // multipleAddorder.add( AddOrderPayload.setOrderPayLoadNonBuilder());
        //multipleAddorder.add( AddOrderPayload.setOrderPayLoadNonBuilder());

        // Use Stream.generate to create an infinite stream of AddOrderPayload objects
        Stream.generate(AddOrderPayload::setOrderPayLoadNonBuilder)
                // Limit the stream to 3 elements and .forEach(multipleAddOrder::add) add it to the multipleAddOrder list
                .limit(4).
                forEach(multipleAddorder::add);
        String addOrderPayload = ordMap.writeValueAsString(multipleAddorder);

        // Send the POST request
       /* Response response1 = given()
                .body(addOrderPayload).when()
                .post("/addOrder").andReturn();*/

        Response response2 = given().body(addOrderPayload).
                when()
                .post("/addOrder").andReturn();

        // VALIDATION USING DESERIALIZING RESPONSE BACK TO POJO first convert response body to string
        String responseBody = response2.getBody().asString();

        // Deserialize JSON response to POJO
        AddOrderPayloadResponse responsePojo = ordMap.readValue(responseBody, AddOrderPayloadResponse.class);

        List<Order> orderResponse = responsePojo.getOrders();
        // orderResponse.stream().map(Order::getId).forEach(pid-> System.out.println("pid = " + pid));
        // orderResponse.stream().map(Order::getUserId).forEach(uid-> System.out.println("userID = " + uid));
        List<String> actualUserIds = orderResponse.stream().map(Order::getUserId).collect(Collectors.toList());

        String orderMessage = responsePojo.getMessage();
        Assert.assertEquals(orderMessage, "Orders added successfully!");

        expectedProductIds.stream().forEach(s -> System.out.println("expectedProductIds = " + s));
        expetecUserIds.stream().forEach(s -> System.out.println("expetecUserIds = " + s));
        expetecdProductName.stream().forEach(s -> System.out.println("expetecProductName = " + s));
        expetecdproductamount.stream().forEach(s -> System.out.println("expetecdproductamount = " + s));
        List<String> expetecUserId = expetecUserIds.stream().collect(Collectors.toList());
     /*   for(String uid:expetecUserId)
        {
            System.out.println(uid);
            Assert.assertTrue(actualUserIds.contains(uid));
        }*/

        // JAVA 8
        expetecUserId.forEach(uid -> {
            Assert.assertTrue(actualUserIds.contains(uid), "Expected user ID not found: " + uid);
        });


        // ProductName validation
        List<String> actualProductNames = orderResponse.stream().map(Order::getProductName).collect(Collectors.toList());
        expetecdProductName.forEach(ProductName -> {
            System.out.println("ProductName = " + ProductName);
            Assert.assertTrue(actualProductNames.contains(ProductName), "Expected ProductName: " + ProductName);
        });


        // Product Amount validation
        List<String> actualProductAmount = orderResponse.stream().map(Order::getProductAmount).collect(Collectors.toList());
        expetecdproductamount.forEach(amount ->
        {
            System.out.println("amount = " + amount);
            Assert.assertTrue(actualProductAmount.contains(amount), "expetecd productamount Not found" + expetecdproductamount);

        });
        //
        // VALIDATIONS USING JSON PATH APPROACH BELOW COMMENTED TO TEST ABOVE APPROACH THAT IS DESERIALIZING RESPONSE BACK TO POJO
        // Validate the response
        // ValidatableResponse Amout = response1.then()
        //        .body("message", equalTo("Orders added successfully!")).and().statusCode(201);// Example assertion; modify as needed*/

        // Extract a list of product amounts from the JSON response using jsonPath
        // List<String> list = response1.jsonPath().getList("orders.product_amount");

        // Print each product amount in the list
        //  list.stream().forEach(l -> System.out.println("l ---------= " + l));

        // Extract a list of product IDs from the JSON response using jsonPath
        //List<String> ProductId = response1.jsonPath().getList("orders.product_id");

        // Print each product ID in the list
        //  ProductId.stream().forEach(s-> System.out.println(s));
        // System.out.println("list SIZE= " + list.size());

       /* List<String> expectedAmounts = List.of(
                "4000"
        );
        System.out.println("expectedAmounts SIZE= " + expectedAmounts.size());
        //Assert.assertEquals(list,expectedAmounts);
        for (String amount : expectedAmounts) {
            Assert.assertTrue(list.contains(amount), "Expected amount not found: " + amount);
        }
        List<String> ExpectedProductId = List.of(
                "2020"
        );
        for(String pid :ExpectedProductId)
        {
            Assert.assertTrue(ProductId.contains(pid));
        }*/

    }

    @Test()
    public void testAddOrderResponseSchemaValidation() throws IOException {

        InputStream createOrderJsonSchema = getClass().getClassLoader().getResourceAsStream("OrderProduct_schema.json");
        // Use Stream.generate to create an infinite stream of AddOrderPayload objects
        Stream.generate(AddOrderPayload::setOrderPayLoadNonBuilder)
                // Limit the stream to 3 elements and .forEach(multipleAddOrder::add) add it to the multipleAddOrder list
                .limit(1).
                forEach(multipleAddorder::add);
        String addOrderPayload = ordMap.writeValueAsString(multipleAddorder);
        ValidatableResponse body = given().body(addOrderPayload).
                when()
                .post("/addOrder").then().assertThat().statusCode(201).body(JsonSchemaValidator.matchesJsonSchema(createOrderJsonSchema));


        // VALIDATION USING DESERIALIZING RESPONSE BACK TO POJO first convert response body to string
/*
        String responseBody = response2.getBody().asString();

        // Deserialize JSON response to POJO
        AddOrderPayloadResponse responsePojo = ordMap.readValue(responseBody, AddOrderPayloadResponse.class);

        List<Order> orderResponse = responsePojo.getOrders();
        List<String> actualUserIds = orderResponse.stream().map(Order::getUserId).collect(Collectors.toList());
        String orderMessage = responsePojo.getMessage();
        Assert.assertEquals(orderMessage, "Orders added successfully!");


        // ProductName validation
        List<String> actualProductNames = orderResponse.stream().map(Order::getProductName).collect(Collectors.toList());
        expetecdProductName.forEach(ProductName -> {
            System.out.println("ProductName = " + ProductName);
            Assert.assertTrue(actualProductNames.contains(ProductName), "Expected ProductName: " + ProductName);
        });
*/

    }
    @Test
    public void addOrderPorductRequestSchemaValidation() throws IOException {
        InputStream createOrderRequestSchema= getClass().getClassLoader().getResourceAsStream("OrderProductRequestSchema.json");
        JSONObject rawSchema = new JSONObject(new String(createOrderRequestSchema.readAllBytes()));
        Schema schema = SchemaLoader.load(rawSchema);
        //InputStream createOrderJsonSchema = getClass().getClassLoader().getResourceAsStream("OrderProduct_schema.json");
        // Use Stream.generate to create an infinite stream of AddOrderPayload objects
        Stream.generate(AddOrderPayload::setOrderPayLoadNonBuilder)
                // Limit the stream to 3 elements and .forEach(multipleAddOrder::add) add it to the multipleAddOrder list
                .limit(1).
                forEach(multipleAddorder::add);
        String addOrderPayload = ordMap.writeValueAsString(multipleAddorder);
        JSONArray jsonArray = new JSONArray(addOrderPayload);
        schema.validate(jsonArray);
        System.out.println("Validatin schema = ");




       /* given().body(addOrderPayload).then().body(JsonSchemaValidator.matchesJsonSchema(createOrderRequestSchema)).
                when()
                .post("/addOrder");*/
    }
}

