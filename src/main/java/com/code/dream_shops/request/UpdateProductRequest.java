package com.code.dream_shops.request;

import com.code.dream_shops.model.Category;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class UpdateProductRequest {
    private Long id;
    private String name;
    private String description;

    private BigDecimal price;
    private int inventory; //number of product that we have

    private String brand;
    private Category category;
}
