package com.ss.apitesting.models.pet;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;

public class PetModel {

    public Long id;
    public Category category;
    public String name;
    public String[] photoUrls;
    public Tag[] tags;
    public String status;

    public PetModel() {
        super();
        this.id = 0L;
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
