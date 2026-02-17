package com.code.dream_shops.service.product;

import com.code.dream_shops.exceptions.ProductNotFoundException;
import com.code.dream_shops.model.Category;
import com.code.dream_shops.model.Product;
import com.code.dream_shops.request.AddProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.code.dream_shops.repository.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private ProductRepository productRepository;
    @Override
    public Product addProduct(AddProductRequest product) {
        return null;
    }

    private Product createProduct(AddProductRequest request,Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getDescription(),
                request.getPrice(),
                request.getInventory(),
                request.getCategory()
        );
    }
    //blkA

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found"));
    }

    @Override
    public void deleteProductBuId(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,
                () -> {throw new ProductNotFoundException("Product Not Found");});
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand);
    }

    @Override
    public List<Product> getProductsByBrandAndCategory(String brand, String category) {
        return productRepository.findByBrandAndCategoryName(brand, category);
    }

    @Override
    public List<Product> getProductsByNameAndCategory(String name, String category) {
        return productRepository.findByNameAndCategoryName(name, category);
    }

    @Override
    public Long countProductByBrandAndName(String brand, String name) {
         return productRepository.countByBrandAndName(brand,name);
    }

}
