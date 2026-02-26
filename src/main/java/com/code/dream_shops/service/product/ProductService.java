package com.code.dream_shops.service.product;


import com.code.dream_shops.controller.ImageController;
import com.code.dream_shops.dto.ImageDto;
import com.code.dream_shops.exceptions.ResourceNotFoundException;
import com.code.dream_shops.model.Category;
import com.code.dream_shops.model.Product;
import com.code.dream_shops.model.Image;
import com.code.dream_shops.request.AddProductRequest;
import com.code.dream_shops.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.code.dream_shops.repository.*;
import com.code.dream_shops.dto.ProductDto;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;
    @Override
    public Product addProduct(AddProductRequest request) {
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName())).orElseGet(() -> {
            Category cat = new Category(request.getCategory().getName());
            return categoryRepository.save(cat);
        });
        request.setCategory(category);
        return productRepository.save(createProduct(request,category));
    }

    private Product createProduct(AddProductRequest request,Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getDescription(),
                request.getPrice(),
                request.getInventory(),
                category
        );
    }


    //blkA

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Product Not Found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id).ifPresentOrElse(productRepository::delete,
                () -> {throw new ResourceNotFoundException("Product Not Found");});
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository :: save)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
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
        return productRepository.findByBrandAndName(brand,name);
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

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products){
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product){
        ProductDto productDto = new ProductDto();
        modelMapper.map(product, productDto);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .toList();
        productDto.setImages(imageDtos);
        return productDto;
    }
}
