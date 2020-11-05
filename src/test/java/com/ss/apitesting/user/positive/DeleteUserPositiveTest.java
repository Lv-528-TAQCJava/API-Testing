package com.ss.apitesting.user.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.PetBuilder;
import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.client.UserClient;
import com.ss.apitesting.models.user.UserModel;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation about user tests")
@Feature("Delete user positive test suite")
public class DeleteUserPositiveTest {
    private UserClient userClient;

    @BeforeClass
    public void init() {
        userClient = new UserClient(ContentType.JSON);
    }

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
}
