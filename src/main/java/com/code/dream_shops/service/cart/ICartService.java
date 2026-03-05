package com.code.dream_shops.service.cart;

import com.code.dream_shops.dto.CartDto;
import com.code.dream_shops.dto.CartItemDto;
import com.code.dream_shops.model.Cart;
import com.code.dream_shops.model.CartItem;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long cartId);
    void clearCart(Long cartId);
    CartDto convertToCartDto(Cart cart);
    Long initializeNewCart();
    CartItemDto convertToCardItemDto(CartItem item);
}
