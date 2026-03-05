package com.code.dream_shops.controller;


import com.code.dream_shops.dto.CartDto;
import com.code.dream_shops.exceptions.ResourceNotFoundException;
import com.code.dream_shops.model.Cart;
import com.code.dream_shops.response.ApiResponse;
import com.code.dream_shops.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/carts")

public class CartController {

    private final ICartService cartService;
    @GetMapping("{id}/my-cart")
    public ResponseEntity<ApiResponse> getCartById(@PathVariable Long id){
        try{
            Cart cart = cartService.getCart(id);
            CartDto cartDto = cartService.convertToCartDto(cart);
            return ResponseEntity.ok(new ApiResponse("Found", cartDto));
        } catch(ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<ApiResponse> clearCart( @PathVariable Long cartId) {
        try {
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Clear Cart Success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{cartId}/cart/total-price")
    public ResponseEntity<ApiResponse> getTotalAmount( @PathVariable Long cartId) {
        try {
            Cart cart = cartService.getCart(cartId);
            CartDto cartDto = cartService.convertToCartDto(cart);
            BigDecimal totalPrice = cartDto.getTotalAmount();
            return ResponseEntity.ok(new ApiResponse("Total Price", totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


}
