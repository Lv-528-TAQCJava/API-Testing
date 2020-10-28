package com.ss.apitesting.userTest;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.client.UserClient;
import com.ss.apitesting.models.user.UserModel;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class GetUserByUsernameTest {

    protected UserClient userClient;
    private UserModel user;
    private final String invalidUsername = "invalidUser";

    @BeforeClass
    public void init() {
        userClient = new UserClient(ContentType.JSON);
    }

    @Test
    public void getUserByValidUsernameTest(){
        user = userClient.createUserData();
        userClient.createNewUser(user);
        userClient.getByUsername(user.username);
        BaseAssertion assertionDelete = new BaseAssertion(userClient.deleteByUsername(user.username));
        assertionDelete.statusCode(200);
    }

    @Test
    public void getUserByInvalidUsernameTest(){
        BaseAssertion assertion = new BaseAssertion(userClient.getByUsername(invalidUsername));
        assertion.statusCode(404);
        assertion.bodyValueEquals("message", "User not found");
    }

    @Test
    public void getUserByEmptyUsernameTest(){
        BaseAssertion assertion = new BaseAssertion(userClient.getByUsername(""));
        assertion.statusCode(405);
    }
}
