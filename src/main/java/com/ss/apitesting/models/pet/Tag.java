package com.ss.apitesting.models.pet;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Tag {

    public Integer id;
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

    public static boolean equals(Object a, Object b) {
        if (a == b) {
            return true;
        }

        if (!(a instanceof Tag)) {
            return false;
        }

        return a.equals(b);
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
