package com.ss.apitesting;

import com.ss.apitesting.client.UserClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateUserTest {

    @Test
    public void createUserTest(){
        UserClient userClient = new UserClient("json");
        Response response = userClient.createUser(1);
        response.then().statusCode(200).log().all();
    }
}
