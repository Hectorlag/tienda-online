package com.example.ventas.repository;

import com.example.ventas.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<Sale, Long> {

    //I get the sales that are not eliminated
    List<Sale> findByDeletedFalse();
}
