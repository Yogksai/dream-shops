package com.code.dream_shops.request;

import com.code.dream_shops.model.Category;
import com.code.dream_shops.model.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.List;
    

@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String description;

    private BigDecimal price;
    private int inventory; //number of product that we have

    private String brand;
    private Category category;

}


