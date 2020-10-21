package com.ss.apitesting.models.pet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @JsonProperty("id")
    public Integer petId;
    @JsonProperty("category")
    public Category category;
    @JsonProperty("name")
    public String name;
    @JsonProperty("photoUrls")
    public String[] photoUrls;
    @JsonProperty("tags")
    public Tag[] tags;
    @JsonProperty("status")
    public String status;

    public PetModel(Integer petId, Category category, String name, String[] photoUrls, Tag[] tags, String status) {
        super();
        this.petId = petId;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public PetModel() {
        super();
        this.petId = 0;
        this.category = new Category();
        this.name = "default";
        this.photoUrls = new String[] {"default"};
        this.tags = new Tag[] {new Tag()};
        this.status = "available";
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", petId)
                .append("category", category)
                .append("name", name)
                .append("photoUrls", photoUrls)
                .append("tags", tags)
                .append("status", status)
                .toString();
    }

}
