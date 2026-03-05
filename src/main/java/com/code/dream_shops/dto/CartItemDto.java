package com.code.dream_shops.dto;


import com.code.dream_shops.model.Cart;
import com.code.dream_shops.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    private Long id;
    private ProductDto product;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
