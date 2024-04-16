package com.example.compras.service;

import com.example.compras.DTO.BuysProductsDTO;
import com.example.compras.model.Buys;

import java.util.List;

public interface IBuysService {

    public List<Buys> getBuys();

    public BuysProductsDTO create(List<Long> idsProducts);

    public void editBuys(Long id, Buys buys);

    public void deleteBuys(Long id);

    public BuysProductsDTO findBuysAndDetails(Long id);


    public Buys find(Long id);
}
