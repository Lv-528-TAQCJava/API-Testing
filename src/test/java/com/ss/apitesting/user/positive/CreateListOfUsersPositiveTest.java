package com.ss.apitesting.user.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.models.user.UserModel;
import com.ss.apitesting.user.UserBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.util.List;

@Epic("Operation about user tests")
@Feature("Create list of users positive test suite")
public class CreateListOfUsersPositiveTest extends UserBaseTest {

    private List<UserModel> userList;

    @Test(description = "Get list of users with valid data")
    public void createListOfUsersWithValidData() {
        userList = userClient.createListOfUsers(2);
        BaseAssertion assertion = new BaseAssertion(userClient.createUserList(userList));
        assertion.statusCode(200);
    }
}
