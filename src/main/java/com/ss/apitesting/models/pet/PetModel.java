package com.ss.apitesting.models.pet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "category",
        "name",
        "photoUrls",
        "tags",
        "status"
})
public class PetModel {

    public Integer id;
    public Category category;
    public String name;
    public String[] photoUrls;
    public Tag[] tags;
    public String status;

    public PetModel(Integer id, Category category, String name, String[] photoUrls, Tag[] tags, String status) {
        super();
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public PetModel() {
        super();
        this.id = 0;
        this.category = null;
        this.name = "default";
        this.photoUrls = new String[] {"default"};
        this.tags = new Tag[] {new Tag()};
        this.status = "available";
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("category", category)
                .append("name", name)
                .append("photoUrls", photoUrls)
                .append("tags", tags)
                .append("status", status)
                .toString();
    }

    public static boolean equals(Object a, Object b) {
        if (a == b) {
            return true;
        }

        if (!(a instanceof PetModel)) {
            return false;
        }

        return a == null? false : a.equals(b);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PetModel)) {
            return false;
        }
        PetModel p = (PetModel) obj;

        return id.equals(p.id) && name.equals(p.name)
                && Category.equals(category, p.category) && status.equals(p.status)
                && Arrays.equals(photoUrls, p.photoUrls) && Arrays.equals(tags, tags);
    }
}
