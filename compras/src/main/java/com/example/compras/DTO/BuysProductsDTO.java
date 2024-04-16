package com.example.compras.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class BuysProductsDTO {

    private Long id_buys;
    private double total_price;
    private List<ProductDTO> productsList;
}
