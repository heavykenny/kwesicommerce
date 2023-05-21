package com.example.kwesicommerce.data.model;

import java.util.ArrayList;
import java.util.List;

public class CategoryModel {
    private int id;
    private String name;

    public CategoryModel() {
        this.name = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
