package com.code.dream_shops.service.cartItem;

import com.code.dream_shops.exceptions.ResourceNotFoundException;
import com.code.dream_shops.model.Cart;
import com.code.dream_shops.model.CartItem;
import com.code.dream_shops.model.Product;
import com.code.dream_shops.repository.CartItemRepository;
import com.code.dream_shops.repository.CartRepository;
import com.code.dream_shops.service.cart.ICartService;
import com.code.dream_shops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{
    private final CartItemRepository cartItemRepository;
    private final ICartService cartService;
    private final CartRepository cartRepository;
    private final IProductService productService;
    @Override
    public CartItem getCartItem(Long cardId, Long productId) {
        Cart cart = cartService.getCart(cardId);
        return cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }

    @Override
    //1. Get the cart
    //2. Get the product
    //3. Check if the product already in the cart
    //4. If Yes, then increase the quantity with the requested quantity
    //5. If No, then initiate a new CartItem entry.
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());

        if(cartItem.getId() == null){
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }else{
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }


    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem cartItem = getCartItem(cartId, productId);
        cart.removeItem(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {
        Cart cart = cartService.getCart(cartId);
        cart.getItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item -> {
                                item.setQuantity(quantity);
                        }
                );
        cartRepository.save(cart);
    }

}
