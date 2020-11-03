package com.ss.apitesting.builder;

import com.ss.apitesting.models.order.StoreModel;
import com.ss.apitesting.models.order.StoreModelString;

/**
 * Gives a possibility to get a new order with wanted properties
 * For example:
 *          OrderModel order = OrderBuilder.orderWith().id(10).petId(4).status("placed").build();
 */
public class OrderBuilder {
    private StoreModel order;
    private StoreModelString orderString;

    private OrderBuilder() {
        order = new StoreModel();
        orderString = new StoreModelString();
    }

    /**
     * Starts building
     * @return builder with possibility to set properties
     */
    public static OrderBuilder orderWith() {
        return new OrderBuilder();
    }

    public OrderBuilder id(Integer id) {
        order.id = id;
        orderString.id = id.toString();
        return this;
    }
    public OrderBuilder idString(String id) {
        orderString.id = id;
        return this;
    }

    public OrderBuilder petId(Integer petId) {
        order.petId = petId;
        orderString.petId = petId.toString();
        return this;
    }
    public OrderBuilder petIdString(String petId) {
        orderString.petId = petId;
        return this;
    }

    public OrderBuilder quantity(Integer quantity) {
        order.quantity = quantity;
        orderString.quantity = quantity.toString();
        return this;
    }
    public OrderBuilder quantityString(String quantity) {
        orderString.quantity = quantity;
        return this;
    }

    public OrderBuilder shipDate(String shipDate) {
        order.shipDate = shipDate;
        order.shipDate = shipDate;
        return this;
    }

    public OrderBuilder status(String status) {
        order.status = status;
        orderString.status = status;
        return this;
    }

    public OrderBuilder complete(boolean complete) {
        order.complete = complete;
        orderString.complete = String.valueOf(complete);
        return this;
    }
    public OrderBuilder completeString(String complete) {
        orderString.complete = complete;
        return this;
    }

    /**
     * Final method
     * @return constructed order. If order was not constructed, then returns default order.
     */
    public StoreModel build() {
        return order;
    }

    public StoreModelString buildString() {
        return orderString;
    }
}
