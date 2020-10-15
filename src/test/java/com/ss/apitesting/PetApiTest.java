package com.ss.apitesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetApiTest {
    @Test
    public void test1() {
        Response response = RestAssured.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
        System.out.println(response.asString());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusCode());
        System.out.println(response.getStatusLine());
        System.out.println(response.getContentType());
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200, statusCode);
    }
}
