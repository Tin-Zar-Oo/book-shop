package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrder {

    private String customerName;
    private String email;
    private String phone;
    private String shippingAddress;
    private String billingAddress;

}
