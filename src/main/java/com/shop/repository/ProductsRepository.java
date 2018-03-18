package com.shop.repository;

import com.shop.domain.Products;
import com.shop.domainDto.ProductsDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ProductsRepository extends CrudRepository<Products, Long> {

    @Override
    List<Products> findAll();

    @Override
    Products save(Products products);

    @Override
    void deleteById(Long id);
}
