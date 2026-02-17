package com.code.dream_shops.service.product;

import com.code.dream_shops.model.Product;
import java.util.List;

public interface IProductService {
    Product addProduct(Product product);

    Product getProductById(Long id);
    void deleteProductBuId(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByBrandAndName(String brand, String name);
    List<Product> getProductsByBrandAndCategory(String brand, String category);

    List<Product> getProductsByNameAndCategory(String name, String category);

    Long countProductByBrandAndName(String brand, String name);
}
