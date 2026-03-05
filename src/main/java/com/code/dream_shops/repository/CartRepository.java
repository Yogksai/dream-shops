package com.code.dream_shops.repository;

import com.code.dream_shops.model.Cart;
import com.code.dream_shops.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
