package com.example.bookshop.controller;

import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.BookId;
import com.example.bookshop.service.BookService;
import com.example.bookshop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final BookService bookService;

    @GetMapping("/add-cart") // /cart/add-cart  -> no need to use model boz no need to show
    public String addToCart(@RequestParam("id") int id,
                            @RequestParam("isbn")String isbn){
       BookId bookId = new BookId();
       bookId.setId(id);
       bookId.setIsbn(isbn);
       Book book = bookService.findById(bookId);
       cartService.addToCart(book);
       return "redirect:/book/list-books";
    }
}
