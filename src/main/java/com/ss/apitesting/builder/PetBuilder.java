package com.ss.apitesting.builder;

import com.ss.apitesting.models.pet.Category;
import com.ss.apitesting.models.pet.PetModel;
import com.ss.apitesting.models.pet.Tag;

/**
 * Gives a possibility to get a new pet with wanted properties
 * For example:
 *           PetModel pet = PetBuilder.petWith().id(4).name("doggo").status("sold").build();
 */
public class PetBuilder {
    private PetModel pet;

    private PetBuilder() {
        pet = new PetModel();
    }

    /**
     * Starts building
     * @return builder with possibility to set properties
     */
    public static PetBuilder petWith() {
        return new PetBuilder();
    }

    public PetBuilder id(Integer id) {
        pet.petId = id;
        return this;
    }

    public PetBuilder category(Category category) {
        pet.category = category;
        return this;
    }

    public PetBuilder name(String name) {
        pet.name = name;
        return this;
    }

    public PetBuilder photoUrls(String[] photoUrls) {
        pet.photoUrls = photoUrls;
        return this;
    }

    public PetBuilder tags(Tag[] tags) {
        pet.tags = tags;
        return this;
    }

    public PetBuilder status(String status) {
        pet.status = status;
        return this;
    }

    /**
     * Final method
     * @return constructed pet. If pet was not constructed, then returns default pet.
     */
    public PetModel build() {
        return pet;
    }
}
