package com.code.dream_shops.service.cartItem;

import com.code.dream_shops.dto.CartItemDto;
import com.code.dream_shops.model.CartItem;

public interface ICartItemService {
    CartItem getCartItem(Long cardId, Long productId);
    void addItemToCart(Long cartId, Long productId, int quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);

}
