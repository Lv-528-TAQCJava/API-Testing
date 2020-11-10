package com.ss.apitesting.client;

import com.ss.apitesting.builder.UserBuilder;
import com.ss.apitesting.models.user.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ss.apitesting.util.ValuesGenerator.generateId;

public class UserClient extends BaseClient {

    private static final String USER_ENTITY = "user";

    public UserClient(ContentType contentType) {
        super(contentType, USER_ENTITY);
    }

    public UserClient(String contentType) {
        super(contentType, USER_ENTITY);
    }

    public UserClient() {
        super(ContentType.JSON, USER_ENTITY);
    }

    /**
     * @param username Allows find user by username (for POST functionality)
     * @return response
     */
    public Response getByUsername(String username) {
        return prepareRequest()
                .pathParam("username", username )
                .get("/{entity}/{username}");
    }

    public Response putUser(UserModel reqBody, String username) {
        return prepareRequest()
                .body(reqBody)
                .put("/{entity}/" + username);
    }

    public Response getUserLogin(String username, String password) {
        return prepareRequest()
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/{entity}/login");
    }

    public Response getUserLogout() {
        return prepareRequest()
                .get("/{entity}/logout");
    }

    public Response createNewUser(UserModel userModel) {
        return prepareRequest()
                .body(userModel)
                .post("/{entity}");
    }

    public Response createUserList(List<UserModel> userList){
        return prepareRequest()
                .body(userList)
                .post("/{entity}/createWithArray");
    }

    public Response deleteByUsername(String username) {
        return prepareRequest()
                .pathParam("username", username)
                .delete("/{entity}/{username}");
    }

    public UserModel createUserData() {
        int userId = generateId();
        return UserBuilder.userWith()
                .id(userId)
                .username("Username" + userId)
                .firstname("User" + userId + "firstname")
                .lastname("User" + userId + "lastname")
                .email("User" + userId + "@gmail.com")
                .password("User" + userId + "password")
                .phone("+380" + RandomStringUtils.randomAlphabetic(9))
                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                .build();
    }

    public UserModel createUserWithoutPassword() {
        int userId = generateId();
        return UserBuilder.userWith()
                .id(userId)
                .username("Username" + userId)
                .firstname("User" + userId + "firstname")
                .lastname("User" + userId + "lastname")
                .email("User" + userId + "@gmail.com")
                .phone("+380" + RandomStringUtils.randomAlphabetic(9))
                .userStatus(Integer.parseInt(RandomStringUtils.randomNumeric(3)))
                .build();
    }

    public List<UserModel> createListOfUsers(int numberOfUser){
        List<UserModel> userList= new ArrayList<>();
        for(int i = 0; i < numberOfUser; i++){
            userList.add(createUserData());
        }
        return userList;
    }

}