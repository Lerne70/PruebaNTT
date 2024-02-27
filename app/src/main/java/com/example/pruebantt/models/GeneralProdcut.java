package com.example.pruebantt.models;

import java.util.List;

public class GeneralProdcut {

    private List<Product> products;

    public GeneralProdcut(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
