package com.code.dream_shops.repository;

import com.code.dream_shops.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.code.dream_shops.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategoryName(String category);

    List<Product> findByName(String name);

    List<Product> findByBrand(String brand);

    List<Product> findByNameAndCategoryName(String name, String category);

    List<Product> findByBrandAndCategoryName(String brand, String category);

    List<Product> findByBrandAndName(String brand, String name);

    Long countByBrandAndName(String brand, String name);

}
