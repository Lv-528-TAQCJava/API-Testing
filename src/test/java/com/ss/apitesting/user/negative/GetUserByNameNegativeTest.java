package com.ss.apitesting.user.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.user.UserBaseTest;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class GetUserByNameNegativeTest extends UserBaseTest {
    private final String invalidUsername = "invalidUser";

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
