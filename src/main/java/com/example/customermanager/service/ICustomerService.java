package com.example.customermanager.service;

import com.example.customermanager.model.Customer;
import com.example.customermanager.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ICustomerService extends IGenerateService<Customer>{
    Optional<Customer> findById(Long id);

    Iterable<Customer> findAllByProvince(Province province);
    Iterable<Customer> findAllByFirstNameContaining(String name);

    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAllByFirstNameContaining(Pageable pageable, String name);
}