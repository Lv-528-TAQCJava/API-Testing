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
public class Tag {

    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;

    public Tag(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Tag() {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag t = (Tag) obj;

        return id.equals(t.id) && name.equals(t.name);
    }
}
