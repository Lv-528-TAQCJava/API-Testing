package com.ss.apitesting.user.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.user.UserBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;


@Epic("Operation about user tests")
@Feature("Delete user negative test suite")
public class DeleteUserNegativeTest extends UserBaseTest {
    @Test
    public void deleteNonexistent() {
        userClient.deleteByUsername("undefined");

        Response given = userClient.getByUsername("undefined");
        BaseAssertion assertGetting = new BaseAssertion(given);
        assertGetting.statusCode(404);

        Response deleted = userClient.deleteByUsername("undefined");
        BaseAssertion assertDeleting = new BaseAssertion(deleted);
        assertDeleting.statusCode(404);
    }

    @Test
    public void deleteAll() {
        Response deleted = userClient.deleteByUsername("%25");
        BaseAssertion assertDeleting = new BaseAssertion(deleted);
        assertDeleting.statusCode(404);
    }

    @Test
    public void deleteUserWithInvalidUsernameTest() {
        Response response = userClient.deleteByUsername("111");
        Assert.assertEquals(response.getStatusCode(), HTTP_NOT_FOUND);
        Assert.assertEquals(response.getContentType(), "");
    }

}
