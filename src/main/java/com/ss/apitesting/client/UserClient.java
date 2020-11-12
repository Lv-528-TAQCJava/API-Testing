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
        log.debug("GET user by name: {}", username);
        return prepareRequest()
                .pathParam("username", username )
                .get("/{entity}/{username}");
    }

    public Response updateUser(UserModel reqBody, String username) {
        log.debug("PUT user with name {} by: {}", username, reqBody);
        return prepareRequest()
                .body(reqBody)
                .put("/{entity}/" + username);
    }

    public Response getUserLogin(String username, String password) {
        log.debug("LOGIN user with name: {}, password: {}", username, password);
        return prepareRequest()
                .queryParam("username", username)
                .queryParam("password", password)
                .get("/{entity}/login");
    }

    public Response getUserLogout() {
        log.debug("LOGOUT user");
        return prepareRequest()
                .get("/{entity}/logout");
    }

    public Response createNewUser(UserModel userModel) {
        log.debug("POST user: {}", userModel);
        return prepareRequest()
                .body(userModel)
                .post("/{entity}");
    }

    public Response createUserList(List<UserModel> userList){
        log.debug("POST list of users with length: {}", userList.size());
        return prepareRequest()
                .body(userList)
                .post("/{entity}/createWithArray");
    }

    public Response deleteByUsername(String username) {
        log.debug("DELETE user by name: {}", username);
        return prepareRequest()
                .pathParam("username", username)
                .delete("/{entity}/{username}");
    }
}