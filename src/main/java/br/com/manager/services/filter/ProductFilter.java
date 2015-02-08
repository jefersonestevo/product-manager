package br.com.manager.services.filter;

import java.io.Serializable;

public class ProductFilter implements Serializable {
    private String name;

    public ProductFilter() {
    }

    public ProductFilter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
