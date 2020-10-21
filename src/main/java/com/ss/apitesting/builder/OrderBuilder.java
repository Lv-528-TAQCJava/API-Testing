package com.ss.apitesting.builder;

import com.ss.apitesting.models.order.StoreModel;

/**
 * Gives a possibility to get a new order with wanted properties
 * For example:
 *          OrderModel order = OrderBuilder.orderWith().id(10).petId(4).status("placed").build();
 */
public class OrderBuilder {
    private StoreModel order;

    private OrderBuilder() {
        order = new StoreModel();
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
        return this;
    }

    public OrderBuilder petId(Integer petId) {
        order.petId = petId;
        return this;
    }

    public OrderBuilder quantity(Integer quantity) {
        order.quantity = quantity;
        return this;
    }

    public OrderBuilder shipDate(String shipDate) {
        order.shipDate = shipDate;
        return this;
    }

    public OrderBuilder status(String status) {
        order.status = status;
        return this;
    }

    public OrderBuilder complete(boolean complete) {
        order.complete = complete;
        return this;
    }

    /**
     * Final method
     * @return constructed order. If order was not constructed, then returns default order.
     */
    public StoreModel build() {
        return order;
    }
}
