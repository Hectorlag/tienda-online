package com.example.ventas.repository;

import com.example.ventas.DTO.BuysProductsDTO;
import com.example.ventas.DTO.SaleDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="compras")
public interface IBuysFeignClient {

  @GetMapping("/buys/getdetails/{id}")
  public ResponseEntity<BuysProductsDTO> findBuysById(@PathVariable("id") Long id);



}
