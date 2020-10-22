package com.ss.apitesting.client;

import com.ss.apitesting.models.order.StoreModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class StoreClient extends BaseClient {
    public StoreClient(ContentType contentType) {
        super(contentType, "store/order");
    }

    public StoreClient(String contentType) {
        super(contentType, "store/order");
    }

    public Response postOrder(String requestBody) {
        return prepareRequest()
                .body(requestBody)
                .urlEncodingEnabled(false)
                .post("/{entity}");
    }
    public Response postOrder(StoreModel storeModel) {
        return prepareRequest()
                .body(storeModel)
                .urlEncodingEnabled(false)
                .post("/{entity}");
    }

    public Response getById(String id) {
        return prepareRequest()
                .pathParam("id", id)
                .get("/{entity}/{id}");
    }

    public Response deleteById(String id) {
        return prepareRequest()
                .pathParam("id", id)
                .delete("/{entity}/{id}");
    }
}
