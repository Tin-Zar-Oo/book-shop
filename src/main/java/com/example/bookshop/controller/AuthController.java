package com.example.bookshop.controller;

import com.example.bookshop.dto.CartItem;
import com.example.bookshop.entity.Customer;
import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.PaymentMethod;
import com.example.bookshop.service.AuthService;
import com.example.bookshop.service.CartService;
import com.example.bookshop.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final CartService cartService;
    private final CustomerService customerService;
    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("customer",new Customer());
        return "register";
    }

    @PostMapping("/save-customer")
    public String saveCustomer(@RequestParam("billingAddress")String billingAddress,
                               @RequestParam("shippingAddress")String shippingAddress,
                               @RequestParam("payment")PaymentMethod method,
                               @ModelAttribute("totalPrice") double totalPrice,
                               Customer customer, BindingResult result){
        Order order = new Order(
                LocalDate.now(),
                billingAddress,
                shippingAddress,
                method,
                totalPrice
        );
        if(result.hasErrors()){
            return "register";
        }

        authService.register(customer,order);
        this.customer = customer;
        return "redirect:/info";
    }

    private Customer customer;
    @GetMapping("/info")
    public ModelAndView checkoutInfo(Map map, @ModelAttribute("totalPrice") double totalPrice){
//      return new ModelAndView("info","cardItem",cartService.getCartItems());
        ModelAndView mv = new ModelAndView();
        mv.addObject("cardItems",cartService.getCartItems());
        mv.addObject("totalPrice",totalPrice);
        mv.addObject("customerInfo",authService.findCustomerInfoByCustomerName(customer.getCustomerName()));
        mv.setViewName("info");
        return mv;

    }

    @GetMapping("/login-error")
    public String loginError(Model model){
        model.addAttribute("loginError",true);
        return "login";
    }

    @GetMapping("/login")
    public String login(){
        if(Objects.isNull(customer)){
            System.out.println("coming login....");
            return "login";
        }
       else{
            System.out.println("customer name:"+customer.getCustomerName());
       customerService.saveCustomerOrderItems(customer);
           return "login";
        }
    }

    @ModelAttribute("totalPrice")
    public double totalAmount(){
        Optional<Double> optionalDouble = cartService
                .getCartItems()
                .stream()
                .map( c -> c.getQuantity() * c.getPrice())
                .reduce((a,b) -> a+b);
        return optionalDouble.orElse(0.0);

    }


}
