package com.example.customermanager.repository;

import com.example.customermanager.model.Customer;
import com.example.customermanager.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);
    Page<Customer> findAll(Pageable pageable);
    Iterable<Customer> findAllByLastName(String name);
    Iterable<Customer> findAllByFirstNameContaining(String firstName);
    Page<Customer> findAllByFirstNameContaining(Pageable pageable, String name);

}
