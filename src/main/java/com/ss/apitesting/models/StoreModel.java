package com.ss.apitesting.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "complete"
})
public class StoreModel {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("petId")
    public Integer petId;
    @JsonProperty("quantity")
    public Integer quantity;
    @JsonProperty("shipDate")
    public String shipDate;
    @JsonProperty("status")
    public String status;
    @JsonProperty("complete")
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
