package com.example.ventas.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

public class SaleDetailsDTO {

    private Long sale_id;
    private LocalDate date;
    private Long buys_id;
    private double total_price;
    private List<ProductDTO> productDTO;
}
