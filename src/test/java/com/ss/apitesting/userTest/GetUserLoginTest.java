package com.ss.apitesting.userTest;

import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.client.UserClient;
import com.ss.apitesting.models.user.UserModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

@Epic("Operation about user tests")
@Feature("Get user login test suite")
public class GetUserLoginTest {
    private UserModel userModel;
    private UserClient userClient;
    @BeforeClass(alwaysRun = true)
    public void init() {
        userClient = new UserClient(ContentType.JSON);
        userModel = UserBuilder.userWith()
                .id(Integer.parseInt(RandomStringUtils.randomNumeric(6)))
                .username(RandomStringUtils.randomAlphabetic(6))
                .firstname(RandomStringUtils.randomAlphabetic(6))
                .lastname(RandomStringUtils.randomAlphabetic(6))
                .email(RandomStringUtils.randomAlphabetic(6) + "@gmail.com")
                .password(RandomStringUtils.randomAlphanumeric(6))
                .phone("+" + RandomStringUtils.randomAlphabetic(10))
                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                .build();
        userClient.createNewUser(userModel);
        Response response = userClient.getByUsername(userModel.username);
        Assert.assertEquals(response.getStatusCode(),HTTP_OK, "Error - user has not been created");
    }

    @Test
    public void getUserLoginTest() {
        Response response = userClient.getUserLogin(userModel.username, userModel.password);
        System.out.println(response.getContentType() + " " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), HTTP_OK);
        response.then().contentType(ContentType.JSON);
    }

    @Test
    public void getUserLoginInvalidPasswordTest() {
        Response response = userClient.getUserLogin(userModel.username, "111111");
        System.out.println(response.getStatusCode() + " " + response.getContentType());
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        response.then().contentType(ContentType.JSON);
    }

    @Test
    public void getUserLoginInvalidUsernameTest() {
        Response response = userClient.getUserLogin("someInvalidUsername", userModel.password);
        System.out.println(response.getStatusCode() + " " + response.getContentType());
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        response.then().contentType(ContentType.JSON);
    }

}
