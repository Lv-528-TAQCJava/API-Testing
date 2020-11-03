package com.ss.apitesting.client;

import com.ss.apitesting.models.order.StoreModel;
import com.ss.apitesting.models.order.StoreModelString;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreClient extends BaseClient {

    public StoreClient(ContentType contentType) {
        super(contentType, "store/order");
    }

    public StoreClient(String contentType) {
        super(contentType, "store/order");
    }

    public StoreClient() {
        super(ContentType.JSON, "store/order");
    }

    public Response getById(int id) {
        return getById(String.valueOf(id));
    }

    public Response getById(String id) {
        log.debug("GET order with ID = " + id);
        return prepareRequest()
                .pathParam("id", id)
                .get("/{entity}/{id}");
    }

    public Response deleteById(int id) {
        return deleteById(String.valueOf(id));
    }

    public Response deleteById(String id) {
        log.debug("DELETE order with ID = " + id);
        return prepareRequest()
                .pathParam("id", id)
                .delete("/{entity}/{id}");
    }

    public Response postOrder(StoreModel storeModel) {
        log.debug("POST order with parameters:\n\t\t" + storeModel.toString());
        return prepareRequest()
                .body(storeModel)
                .urlEncodingEnabled(false)
                .post("/{entity}");
    }

    public Response postOrder(StoreModelString storeModel) {
        log.debug("POST order with parameters:\n\t\t" + storeModel.toString());
        return prepareRequest()
                .body(storeModel)
                .urlEncodingEnabled(false)
                .post("/{entity}");
    }

}
