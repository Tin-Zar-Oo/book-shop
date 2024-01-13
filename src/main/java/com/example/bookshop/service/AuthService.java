package com.example.bookshop.service;

import com.example.bookshop.dao.CustomerDao;
import com.example.bookshop.dao.RoleDao;
import com.example.bookshop.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final CustomerDao customerDao;
    private final RoleDao roleDao;

    public void register(Customer customer){

    }
}
