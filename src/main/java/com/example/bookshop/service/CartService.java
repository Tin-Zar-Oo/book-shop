package com.example.bookshop.service;

import com.example.bookshop.dto.CartItem;
import com.example.bookshop.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    // come from entity -> card item -> cart

    private final CartBean cartBean;
    //to count item -> 1,2,3
    public void addToCart(Book book){
    cartBean.addCartItem(toCartItem(book));
    }

    public Integer cartSize(){
       return cartBean.cartSize();
    }

    // adding book to cart item
    public CartItem toCartItem(Book book){
        return new CartItem(
                book.getId(),
                book.getIsbn(),
                book.getPrice(),
                book.getTitle(),
                1
        );
    }

}