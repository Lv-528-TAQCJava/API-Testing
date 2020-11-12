package com.ss.apitesting.models.pet;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdatePetDto {
    public String id;
    public String name;
    public String status;

    public UpdatePetDto(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
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
