package com.code.dream_shops.repository;

import com.code.dream_shops.model.Category;
import com.code.dream_shops.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category,Long> {
    Category findByName(String name);
}
