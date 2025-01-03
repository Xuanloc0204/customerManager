package com.example.customermanager.service;

import com.example.customermanager.model.DTO.ICountCustomer;
import com.example.customermanager.model.DTO.ProvinceDTO;
import com.example.customermanager.model.Province;
import com.example.customermanager.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinceService implements IProvinceService {
    @Autowired
    private IProvinceRepository provinceRepository;

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Page<Province> findAll(Pageable pageable) {
        return provinceRepository.findAll(pageable);
    }

    @Override
    public Optional<Province> findById(Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public void save(Province province) {
        provinceRepository.save(province);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.deleteById(id);
    }

    @Override
    public Iterable<ProvinceDTO> countCustomerByProvice() {
        return provinceRepository.countCustomerByProvince();
    }

    @Override
    public Iterable<ICountCustomer> getCountCustomers() {
        return provinceRepository.getCountCustomers();
    }

}
