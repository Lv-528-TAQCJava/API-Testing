package com.ss.apitesting.models.order;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StoreModel {

    public Integer id;
    public Integer petId;
    public Integer quantity;
    public String shipDate;
    public String status;
    public Boolean complete;

    public StoreModel(Integer id, Integer petId, Integer quantity, String shipDate, String status, Boolean complete) {
        super();
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public StoreModel() {
        super();
        this.id = 0;
        this.petId = 0;
        this.quantity = 0;
        this.shipDate = "2020-10-21T15:07:07.347Z";
        this.status = "placed";
        this.complete = true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("petId", petId)
                .append("quantity", quantity)
                .append("shipDate", shipDate)
                .append("status", status)
                .append("complete", complete)
                .toString();
    }
}
