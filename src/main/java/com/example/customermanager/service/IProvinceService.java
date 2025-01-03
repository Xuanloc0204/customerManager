package com.example.customermanager.service;

import com.example.customermanager.model.DTO.ICountCustomer;
import com.example.customermanager.model.DTO.ProvinceDTO;
import com.example.customermanager.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProvinceService {
    Iterable<Province> findAll();

    Page<Province> findAll(Pageable pageable);

    Optional<Province> findById(Long id);

    void save(Province province);

    void remove(Long id);

    Iterable<ProvinceDTO> countCustomerByProvice();

    Iterable<ICountCustomer> getCountCustomers();
}
