package com.example.compras.model;

import com.example.compras.DTO.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Buys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_buys;

    private double total_price;
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
    @ElementCollection
    @CollectionTable(name = "buys_products", joinColumns = @JoinColumn(name = "buy_id"))
    @Column(name = "product_id")
    private List<Long> productsIdsList;
}
