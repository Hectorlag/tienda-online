package com.example.ventas.service;

import com.example.ventas.DTO.SaleDetailsDTO;
import com.example.ventas.model.Sale;

import java.util.List;

public interface ISaleService {

    public List<Sale> getSales();

    public void createSale(Long idBuys);

    public void editSale(Long id, Sale s);

    public void deleteSale(Long id);

    public Sale findSale(Long id);

    SaleDetailsDTO findSalewithBuys(Long salesId);
}
