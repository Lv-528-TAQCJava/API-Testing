package com.ss.apitesting.models.pet;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Category {

    public Integer id;
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

    public static boolean equals(Object a, Object b) {
        if (a == b) {
            return true;
        }

        if (!(a instanceof Category)) {
            return false;
        }

        return a.equals(b);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Category)) {
            return false;
        }
        Category c = (Category) obj;

        return id.equals(c.id) && name.equals(c.name);
    }
}
