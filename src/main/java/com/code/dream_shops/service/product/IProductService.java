package com.code.dream_shops.service.product;

import com.code.dream_shops.model.Product;
import com.code.dream_shops.request.AddProductRequest;
import com.code.dream_shops.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest product);

    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(UpdateProductRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrand(String brand);

    List<Product> getProductsByBrandAndName(String brand, String name);
    List<Product> getProductsByBrandAndCategory(String brand, String category);

    List<Product> getProductsByNameAndCategory(String name, String category);

    Long countProductByBrandAndName(String brand, String name);
}
