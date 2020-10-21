package com.ss.apitesting;
import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.client.UserClient;
import com.ss.apitesting.models.user.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;

import org.testng.Assert;
import org.testng.annotations.*;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.is;

public class UpdateUserTest {
    private UserModel userModel;
    private String userName;
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
    public  void deleteUser() {
        userClient.deleteByUsername(userName);
        userName = null;
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
}
