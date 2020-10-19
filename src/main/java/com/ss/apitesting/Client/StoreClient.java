package com.ss.apitesting.Client;

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

    /**
     * Provides JSON string for postOrder method parameter
     */
    public String prepareOrder(int id, int petId, int quantity,
                               String shipDate, String status, Boolean complete) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", Integer.toString(id));
        requestParams.put("petId", Integer.toString(petId));
        requestParams.put("quantity", Integer.toString(quantity));
        requestParams.put("shipDate", shipDate);
        requestParams.put("status", status);
        requestParams.put("complete", complete);

        return requestParams.toJSONString();
    }

    //public Response getById(String id) - implemented in BaseClient

    //public Response deleteById(String id) - implemented in BaseClient
}
