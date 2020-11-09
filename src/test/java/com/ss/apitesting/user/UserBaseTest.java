package com.ss.apitesting.user;

import com.ss.apitesting.BaseTest;
import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.client.UserClient;
import com.ss.apitesting.models.user.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static java.net.HttpURLConnection.HTTP_OK;

public class UserBaseTest extends BaseTest {
    protected UserClient userClient;
    protected UserModel userModel;

    @Override
    protected String getLoggerName() {
        return "UserTest";
    }

    @Override
    @BeforeClass
    public void beforeClass() {
        super.beforeClass();
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
        Assert.assertEquals(response.getStatusCode(), HTTP_OK, "Error - user has not been created");
    }

    @AfterClass
    public void clearUp() {
        userClient.deleteByUsername(userModel.username);
    }
}
