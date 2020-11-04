package com.ss.apitesting.models.pet;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StringPetModel {

    public String  id;
    public String name;
    public String status;

    public StringPetModel(String id, String name, String status) {
        super();
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public StringPetModel() {
        super();
        this.id = "0";
        this.name = "default";
        this.status = "available";
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("status", status)
                .toString();
    }
}
