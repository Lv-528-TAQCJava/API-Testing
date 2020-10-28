package com.ss.apitesting.userTest;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.client.UserClient;
import com.ss.apitesting.models.user.UserModel;
import io.restassured.http.ContentType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateUserTest {

    protected UserClient userClient;
    private List<UserModel> userList;
    private UserModel user;

    @BeforeMethod
    public void init() {
        userClient = new UserClient(ContentType.JSON);
        userList = new ArrayList<>();
    }

    @AfterMethod
    public void deleteUsers(){
        if(userList.isEmpty()) {
            BaseAssertion assertionTest = new BaseAssertion(userClient.deleteByUsername(user.username));
            assertionTest.bodyValueEquals("code", 200);
        }else {
            for (UserModel user : userList) {
                BaseAssertion assertion = new BaseAssertion(userClient.deleteByUsername(user.username));
                assertion.statusCode(200);
            }
        }
    }

    @Test
    public void createUserWithValidData(){
        user = userClient.createUserData();
        BaseAssertion assertion = new BaseAssertion(userClient.createNewUser(user));
        assertion.bodyValueEquals("code", 200);
    }

    @Test
    public void createArrayOfUsersWithValidData(){
        userList = userClient.createListOfUsers(2);
        BaseAssertion assertion = new BaseAssertion(userClient.createUserList(userList));
        assertion.statusCode(200);
    }

}
