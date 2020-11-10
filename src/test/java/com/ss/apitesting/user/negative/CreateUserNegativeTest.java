package com.ss.apitesting.user.negative;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.client.UserClient;
import com.ss.apitesting.models.user.UserModel;
import com.ss.apitesting.util.ValuesGenerator;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation about user tests")
@Feature("Create user negative test suite")
public class CreateUserNegativeTest {
    private UserClient userClient;

    @BeforeClass
    public void init() {
        userClient = new UserClient(ContentType.JSON);
    }

    @DataProvider(name = "invalidUsers")
    public Object[] data() {
        return new Object[]{
                UserBuilder.userWith().id(ValuesGenerator.generateId(1000, 10000))
                        .username("karl")
                        .email("karlovich@gmail.com")
                        .password(null)
                        .build(),

                UserBuilder.userWith().id(ValuesGenerator.generateId(1000, 10000))
                        .username(null)
                        .email("karlovich@gmail.com")
                        .password("djkassjak")
                        .build(),

                UserBuilder.userWith().id(ValuesGenerator.generateId(1000, 10000))
                        .username("karl")
                        .email(null)
                        .password("dafad")
                        .build()
        };
    }

    @Test(dataProvider = "invalidUsers", description = "Create user without password")
    public void createInvalidUserTest(UserModel user) {
        Response created = userClient.createNewUser(user);
        BaseAssertion assertCreating = new BaseAssertion(created);
        assertCreating.statusCode(400);

        Response given = userClient.getByUsername(user.username);
        BaseAssertion assertGetting = new BaseAssertion(given);
        assertGetting.statusCode(404);
    }

}
