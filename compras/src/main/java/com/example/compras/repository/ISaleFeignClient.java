package com.example.compras.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ventas", url="http://localhost:9007/sales")
public interface ISaleFeignClient {

        @PostMapping("/create/{idBuys}") // Endpoint to create a sale in the sales service
        void createSales(@PathVariable("idBuys") Long idBuys); // I send the purchase ID as part of the URL
    }

