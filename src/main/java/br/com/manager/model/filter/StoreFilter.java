package br.com.manager.model.filter;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class StoreFilter implements Serializable {
    private String name;
    private String street;

    public StoreFilter() {
    }

    public StoreFilter(String name, String street) {
        this.name = name;
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
