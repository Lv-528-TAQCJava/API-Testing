package com.ss.apitesting.UserTest;

import com.ss.apitesting.client.UserClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class CreateUsersWithArrayTest {

    @Test
    public void createUsersWithArrayTest(){
        UserClient userClient = new UserClient("json");
        Response response = userClient.createUserArray();
        response.then().statusCode(200).log().all();
    }
}
