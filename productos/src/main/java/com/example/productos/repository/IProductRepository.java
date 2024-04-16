package com.example.productos.repository;

import com.example.productos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

     // I get the products that are not eliminated
      List<Product> findByDeletedFalse();

}
