package com.example.compras.repository;

import com.example.compras.model.Buys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBuysRepository extends JpaRepository<Buys, Long> {

    //I get the products that are not eliminated
    List<Buys> findByDeletedFalse();
}
