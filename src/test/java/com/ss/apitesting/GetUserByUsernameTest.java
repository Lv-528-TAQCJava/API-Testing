package com.ss.apitesting;

import com.ss.apitesting.Client.UserClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class GetUserByUsernameTest {

    @Test
    public void userFindByUsername(){
        UserClient userClient = new UserClient("json");
        Response response = userClient.getByUsername("user1");
        response.then().body("username", is("user1")).statusCode(200).log().all();
    }

}
