package com.hv.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hv.Pojo.AddOrderPayload;
import com.hv.Pojo.AddOrderPayloadBuilderPattern;
import com.hv.Pojo.AddOrderPayloadBuilderPatternLombok;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddOrderAPITestBuilderPattern extends BaseTest {

    AddOrderPayload order1 = AddOrderPayload.setOrderPayLoadNonBuilder();
    List<AddOrderPayload> multipleAddorder= new ArrayList<>();
    ObjectMapper ordMap =  new ObjectMapper();
    {}


    // USING BLDER PATTERN
    //AddOrderPayloadBuilderPattern.Builder builder2 = new AddOrderPayloadBuilderPattern.Builder();
    AddOrderPayloadBuilderPattern builder1 =  AddOrderPayloadBuilderPattern.setOrderDataFromBuilder();


   // List<AddOrderPayloadBuilderPattern> multipleAddorderBuilder= new ArrayList<>();
    List<AddOrderPayloadBuilderPattern> multipleAddorderBuilder = new ArrayList<AddOrderPayloadBuilderPattern>();
    ObjectMapper ordMap1 =  new ObjectMapper();

   // USING LOMBOK BUILDER 1. create lombok buider class object 2. create array list of type  class 3. create object mapper
  AddOrderPayloadBuilderPatternLombok orderPayloadBuilderPatternLombok = AddOrderPayloadBuilderPatternLombok.setOrderDataLombok();


   List<AddOrderPayloadBuilderPatternLombok> lombokList = new ArrayList<>();
   ObjectMapper lombokMap= new ObjectMapper();

    public AddOrderAPITestBuilderPattern() throws JsonProcessingException {
    }

    @Test
    public void testAddOrder() throws JsonProcessingException {
        // Define the payload
      //  addOrderPayloadD.getProductAmount()


       // Non Builder()
     multipleAddorder.add(order1);
     //multipleAddorder.add(order2);
    // multipleAddorder.add(order3);
     //String addOrderPayload = ordMap.writeValueAsString(multipleAddorder);


        // Builder pattern ()
        multipleAddorderBuilder.add(builder1);
        String productAmount = builder1.getProductAmount();
        System.out.println("productAmount ======= " + productAmount);


        // object mapper to serialize the pojo
        String addOrderPayloadBuilder = ordMap1.writeValueAsString(multipleAddorderBuilder);

        // OBject mapper lombok
        lombokList.add(orderPayloadBuilderPatternLombok);
        String addOrderPayloadLombok = lombokMap.writeValueAsString(lombokList);

       // System.out.println("addOrderPayload = " + addOrderPayload);
        // Send the POST request
        Response response1 = given()
                .body(addOrderPayloadLombok).when()
                .post("/addOrder").andReturn();

        // Validate the response
        ValidatableResponse Amout = response1.then()
                .body("message", equalTo("Orders added successfully!")).
                        and().
                        statusCode(201);// Example assertion; modify as needed*/
        List<String> list = response1.jsonPath().getList("orders.product_amount");
        list.stream().forEach(l -> System.out.println("l ---------= " + l));
        List<String> ProductId = response1.jsonPath().getList("orders.product_id");
        ProductId.stream().forEach(s-> System.out.println(s));
        System.out.println("list SIZE= " + list.size());

        List<String> expectedAmounts = List.of(
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
        }

    }
}

