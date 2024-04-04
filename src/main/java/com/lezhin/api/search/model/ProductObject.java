package com.lezhin.api.search.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductObject {
    private List<Product> product_list;
}
