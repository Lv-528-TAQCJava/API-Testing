package com.ss.apitesting.user;

import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.client.UserClient;
import com.ss.apitesting.models.user.UserModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.is;

@Epic("Operation about user tests")
@Feature("Update user test suite")
public class UpdateUserTest {
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

    @AfterMethod
    public void deleteUser() {
        userClient.deleteByUsername(userModel.username);
    }


    @Test
    public void updateUserTest() {
        UserModel updatedModel;
        updatedModel = userModel;
        updatedModel.setFirstname("Drake");
        userClient.putUser(updatedModel, userModel.username);
        Response response = userClient.getByUsername(userModel.username);
        response.then().body("firstName", is("Drake"));
        response.then().statusCode(200);
        response.then().contentType(ContentType.JSON);

    }

    @Test
    public void updateUserNullPropTest() {
        UserModel updatedModel;
        updatedModel = userModel;
        updatedModel.setFirstname("");
        updatedModel.setLastname("");
        userClient.putUser(updatedModel, userModel.username);
        Response response = userClient.getByUsername(userModel.username);
        response.then().body("firstName", is(""));
        response.then().body("lastName", is(""));
        response.then().contentType(ContentType.JSON);
    }
}
