package com.ss.apitesting.client;

import com.ss.apitesting.models.user.UserModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserClient extends BaseClient {

    public UserClient(ContentType contentType) {
        super(contentType, "user");
    }

    public UserClient(String contentType) {
        super(contentType, "user");
    }

    public UserClient() {
        super(ContentType.JSON, "user");
    }

    /**
     *
     * @param username Allows find user vy username (for POST functionality)
     * @return
     */
    public Response getByUsername(String username) {
        return prepareRequest()
                .pathParam("username", username )
                .urlEncodingEnabled(false)
                .get("/{entity}/{username}");
    }

    public Response putUser(UserModel reqBody, String username) {
        return prepareRequest()
                .body(reqBody)
                .urlEncodingEnabled(false)
                .put("/{entity}/" + username);
    }

    /**
     * Create new user
     * @param userModel
     * @return
     */
    public Response createNewUser(UserModel userModel) {
        return prepareRequest()
                .body(userModel)
                .post("/{entity}");
    }

    public Response deleteByUsername(String username) {
        return prepareRequest()
                .pathParam("username", username)
                .delete("/{entity}/{username}");
    }
}
