package com.example.ventas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sale;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private Long id_buys;
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;
}
