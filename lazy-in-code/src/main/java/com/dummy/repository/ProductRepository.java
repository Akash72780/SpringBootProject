package com.dummy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dummy.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
