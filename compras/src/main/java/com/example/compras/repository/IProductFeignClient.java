package com.example.compras.repository;

import com.example.compras.DTO.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productos", url = "http://localhost:9005")
public interface IProductFeignClient {

    @GetMapping("/products/get/{id}") // Product service endpoint to get a product by ID
    ProductDTO getProductById(@PathVariable("id") Long id);
}
