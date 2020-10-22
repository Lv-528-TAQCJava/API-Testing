package com.ss.apitesting.models.pet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name"
})
public class Category {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;

    public Category(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Category() {
        super();
        this.id = 0;
        this.name = "default";
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }

}