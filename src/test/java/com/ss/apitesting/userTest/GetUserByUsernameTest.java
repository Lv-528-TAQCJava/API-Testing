package com.ss.apitesting.userTest;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.client.UserClient;
import io.restassured.response.Response;
import org.testng.annotations.Ignore;
import com.ss.apitesting.models.user.UserModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Operation about user tests")
@Feature("Get user by username test suite")
public class GetUserByUsernameTest {

    protected UserClient userClient;
    private UserModel user;
    private final String invalidUsername = "invalidUser";

    @BeforeClass
    public void init() {
        userClient = new UserClient(ContentType.JSON);
    }

    @Test(description = "Get user with existing username")
    public void getUserByValidUsernameTest(){
        user = userClient.createUserData();
        userClient.createNewUser(user);

        BaseAssertion assertion = new BaseAssertion(userClient.getByUsername(user.username));
        assertion.defaultAsserts().bodyValueEquals("username", user.username);

        userClient.deleteByUsername(user.username).then().statusCode(200);
    }

    @Test(description = "Get user with non-existent username")
    public void getUserByInvalidUsernameTest(){
        BaseAssertion assertion = new BaseAssertion(userClient.getByUsername(invalidUsername));
        assertion.statusCode(404).contentType(ContentType.JSON);
        assertion.bodyValueEquals("message", "User not found");
    }

    @Test(description = "Get user with empty username")
    public void getUserByEmptyUsernameTest(){
        BaseAssertion assertion = new BaseAssertion(userClient.getByUsername(""));
        assertion.statusCode(405);
    }
}
