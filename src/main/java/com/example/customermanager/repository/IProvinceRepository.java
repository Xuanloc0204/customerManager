package com.example.customermanager.repository;

import com.example.customermanager.model.DTO.ICountCustomer;
import com.example.customermanager.model.DTO.ProvinceDTO;
import com.example.customermanager.model.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IProvinceRepository extends PagingAndSortingRepository<Province, Long> {
    
    @Query(value = "SELECT p.name as name, COUNT(c.id) as number FROM province p LEFT JOIN customer c ON p.id = c.province_id GROUP BY p.name", nativeQuery = true)
    Iterable<ICountCustomer> getCountCustomers();

    Page<Province> findAll(Pageable pageable);

    @Query(value = "SELECT p.id as id, p.name as name, COUNT(c.id) as count FROM province p LEFT JOIN customer c ON p.id = c.province_id GROUP BY p.id, p.name", nativeQuery = true)
    Iterable<ProvinceDTO> countCustomerByProvince();

    @Modifying
    @Transactional
    void deleteById(Long id);

    Iterable<Province> findByName(String name);

    Optional<Province> findById(Long id);

    void save(Province province);

    Iterable<Province> findAll();
}
