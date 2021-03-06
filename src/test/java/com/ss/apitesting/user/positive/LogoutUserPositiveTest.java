package com.ss.apitesting.user.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.user.UserBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;

@Epic("Operation about user tests")
@Feature("Logout user positive test suite")
public class LogoutUserPositiveTest extends UserBaseTest {

    @Test
    public void getUserLogoutTest() {
        Response response = userClient.getUserLogout();
        Assert.assertEquals(response.getStatusCode(), HTTP_OK);
        BaseAssertion baseAssertion = new BaseAssertion(response);
        baseAssertion.contentType(ContentType.JSON);
    }
}
