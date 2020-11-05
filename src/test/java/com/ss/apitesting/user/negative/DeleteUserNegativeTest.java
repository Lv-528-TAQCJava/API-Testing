package com.ss.apitesting.user.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.client.UserClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


@Epic("Operation about user tests")
@Feature("Delete user negative test suite")
public class DeleteUserNegativeTest {
    private UserClient userClient;

    @BeforeClass
    public void init() {
        userClient = new UserClient(ContentType.JSON);
    }

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
}
