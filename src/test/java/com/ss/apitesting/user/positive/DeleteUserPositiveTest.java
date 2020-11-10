package com.ss.apitesting.user.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.models.user.UserModel;
import com.ss.apitesting.user.UserBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;

@Epic("Operation about user tests")
@Feature("Delete user positive test suite")
public class DeleteUserPositiveTest extends UserBaseTest {
    @DataProvider(name = "deleteUser")
    public Object[][] data() {
        return new Object[][]{
                {
                        UserBuilder.userWith().id(1).username("ilonahk").build()
                },
                {
                        UserBuilder.userWith().id(2).username("ik32").build()
                },
                {
                        UserBuilder.userWith().id(3).username("ilov+nahk").build()
                }
        };
    }

    @Test(dataProvider = "deleteUser")
    public void deleteUser(UserModel user) {
        Response created = userClient.createNewUser(user);
        BaseAssertion assertCreation = new BaseAssertion(created);
        assertCreation.statusCode(200);

        Response deleted = userClient.deleteByUsername(user.username);
        BaseAssertion assertDeleting = new BaseAssertion(deleted);
        assertDeleting.statusCode(200);

        Response given = userClient.getByUsername(user.username);
        BaseAssertion assertGetting = new BaseAssertion(given);
        assertGetting.statusCode(404);
    }

    @Test
    public void deleteUserTest() {
        Response response = userClient.deleteByUsername(userModel.username);
        Assert.assertEquals(response.getStatusCode(), HTTP_OK);
    }
}
