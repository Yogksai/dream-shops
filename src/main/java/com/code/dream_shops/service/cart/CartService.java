package com.code.dream_shops.service.cart;

import com.code.dream_shops.dto.CartDto;
import com.code.dream_shops.dto.CartItemDto;
import com.code.dream_shops.exceptions.ResourceNotFoundException;
import com.code.dream_shops.model.Cart;
import com.code.dream_shops.model.CartItem;
import com.code.dream_shops.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService implements ICartService{
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    @Override
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(()-> new ResourceNotFoundException("Cart Not Found"));
    }
    @Transactional
    @Override
    public void clearCart(Long cartId) {
        cartRepository.findById(cartId).ifPresentOrElse(cartRepository::delete,
                () -> {throw new ResourceNotFoundException("Cart Not Found");});
    }
    @Override
    public Long initializeNewCart() {
        Cart newCart = new Cart();
        // НЕ вызывай setId вручную!

        Cart savedCart = cartRepository.save(newCart);
        return savedCart.getId(); // Здесь ID уже будет заполнен базой (1, 2, 3...)
    }

    @Override
    public CartDto convertToCartDto(Cart cart) {
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        List<CartItemDto> itemDtos = cart.getItems()
                .stream()
                .map(this::convertToCardItemDto) // Вот тут вызывается твоя логика с расчетом цен!
                .toList();
        cartDto.setItems(new HashSet<>(itemDtos));
        cartDto.setTotalAmount(cart.getItems().stream()
                                                    .map(item -> item.getProduct().getPrice()
                                                            .multiply(new BigDecimal(item.getQuantity())))
                                                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        return cartDto;
    }

    public CartItemDto convertToCardItemDto(CartItem item) {
        CartItemDto cartItemDto = modelMapper.map(item, CartItemDto.class);
        BigDecimal unitPrice = item.getProduct().getPrice();
        cartItemDto.setUnitPrice(unitPrice);
        cartItemDto.setTotalPrice(unitPrice.multiply(new BigDecimal(item.getQuantity())));
        return cartItemDto;
    }
//        CartDto cartDto = modelMapper.map(cart, CartDto.class);
//        cartDto.setTotalAmount(cart.getItems().stream()
//                                                    .map(item -> item.getProduct().getPrice()
//                                                            .multiply(new BigDecimal(item.getQuantity())))
//                                                    .reduce(BigDecimal.ZERO, BigDecimal::add));
//        return cartDto;
}


