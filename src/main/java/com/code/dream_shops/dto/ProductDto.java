package com.code.dream_shops.dto;

import com.code.dream_shops.model.Category;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonPropertyOrder({ "id", "name", "brand", "price", "inventory", "description", "category", "images" })
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private Category category;
    private String description;
    private List<ImageDto> images;

}
