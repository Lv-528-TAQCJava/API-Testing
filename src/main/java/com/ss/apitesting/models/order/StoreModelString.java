package com.ss.apitesting.models.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A variation of StoreModel, that has only string fields, which is useful for negative test cases
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "complete"
})
public class StoreModelString {

    @JsonProperty("id")
    public String id;
    @JsonProperty("petId")
    public String petId;
    @JsonProperty("quantity")
    public String quantity;
    @JsonProperty("shipDate")
    public String shipDate;
    @JsonProperty("status")
    public String status;
    @JsonProperty("complete")
    public String complete;

    public StoreModelString(String id, String petId, String quantity, String shipDate, String status, String complete) {
        super();
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.status = status;
        this.complete = complete;
    }

    public StoreModelString() {
        super();
        this.id = "0";
        this.petId = "0";
        this.quantity = "0";
        this.shipDate = "2020-10-21T15:07:07.347Z";
        this.status = "placed";
        this.complete = "true";
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
