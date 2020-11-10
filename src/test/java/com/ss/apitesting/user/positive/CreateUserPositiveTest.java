package com.ss.apitesting.user.positive;

import com.ss.apitesting.assertion.BaseAssertion;
import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.models.user.UserModel;
import com.ss.apitesting.user.UserBaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Epic("Operation about user tests")
@Feature("Create user positive test suite")
public class CreateUserPositiveTest extends UserBaseTest {

    @DataProvider(name = "users")
    public Object[][] data() {
        return new Object[][]{
                {
                        UserBuilder.userWith().id(1).username("ilonahk").firstname("ilona")
                                .lastname("hryhorak").email("ilona@gmail.com").password("mpk3040h")
                                .phone("380974298101").build()
                },
                {
                        UserBuilder.userWith().id(Integer.MAX_VALUE).username("77ilonffahk").firstname("ilonvdsa")
                                .lastname("hryhovfvrak").email("ds7ilona@gmail.com").password("mpk+3040h")
                                .phone("380974298101").build()
                },
                {
                        UserBuilder.userWith().id(100).username("7").email("ds7ilonagml.com").password("mpk+3040h")
                                .phone("380974298101").build()
                },
                {
                        UserBuilder.userWith().id(100).username("7").email("ds7ilona@gmail.com").password("mpk+3040h")
                                .phone("3809742923975395725732892758101").build()
                }
        };
    }

    @Test(dataProvider = "users")
    public void createUser(UserModel user) {
        Response created = userClient.createNewUser(user);
        BaseAssertion assertCreating = new BaseAssertion(created);
        assertCreating.statusCode(200);

        Response given = userClient.getByUsername(user.username);
        BaseAssertion assertGetting = new BaseAssertion(given);
        assertGetting.statusCode(200);
    }

    @Test(description = "Create user with valid data")
    public void createUserWithValidData(){
        userModel = userClient.createUserData();
        BaseAssertion assertion = new BaseAssertion(userClient.createNewUser(userModel));
        assertion.statusCode(200);
    }
}
