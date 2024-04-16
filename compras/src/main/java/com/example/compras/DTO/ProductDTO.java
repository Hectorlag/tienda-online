package com.example.compras.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDTO {

    private Long id;
    private String name;
    private String brand;
    private double price;
}
