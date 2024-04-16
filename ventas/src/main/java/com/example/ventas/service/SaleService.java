package com.example.ventas.service;

import com.example.ventas.DTO.BuysProductsDTO;
import com.example.ventas.DTO.ProductDTO;
import com.example.ventas.DTO.SaleDetailsDTO;
import com.example.ventas.excepcion.FeignRequestException;
import com.example.ventas.excepcion.SaleNotFoundExcepcion;
import com.example.ventas.model.Sale;
import com.example.ventas.repository.IBuysFeignClient;
import com.example.ventas.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService implements ISaleService{

    // Service buys
    @Autowired
    private IBuysFeignClient iBuysFeignClient;

    @Autowired
    private ISaleRepository saleRepo;

    @Override
    public List<Sale> getSales() {

       return saleRepo.findByDeletedFalse();
    }

    @Override
    public void createSale(Long idBuys) {

        Sale sale = new Sale();
        sale.setDate(LocalDate.now());
        sale.setId_buys(idBuys);

        saleRepo.save(sale);

    }

    @Override
    public void editSale(Long id, Sale s) {

        //I'm looking for the product
       Sale sale = this.findSale(id);
        //Set the data
       sale.setId_sale(s.getId_sale());
       sale.setDate(s.getDate());
       sale.setId_buys(s.getId_buys());

       saleRepo.save(sale);
    }

    @Override
    public void deleteSale(Long id) {

        Sale s = this.findSale(id);
        s.setDeleted(true);

        saleRepo.save(s);
    }

    // I only return the sale
    @Override
    public Sale findSale(Long id) {

        Sale s = saleRepo.findById(id).orElse(null);
        if(s == null){
            throw new SaleNotFoundExcepcion("No sale found with the" +  id  + " id");
        }else{
            return s;
        }
    }

    // I return the sale and purchase
    @Override
    public SaleDetailsDTO findSalewithBuys(Long salesId) {

        Sale sale = saleRepo.findById(salesId).orElse(null);
        if (sale == null) {
            throw new SaleNotFoundExcepcion("Sale with id " + salesId + " not found");
        }
        // Fetch purchase details using Feign
        ResponseEntity<BuysProductsDTO> responseEntity = iBuysFeignClient.findBuysById(salesId);

        // Check the response status
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            BuysProductsDTO buysProductsDTO = responseEntity.getBody();

            SaleDetailsDTO saleDetailsDTO = new SaleDetailsDTO();
            // Set data
            saleDetailsDTO.setSale_id(sale.getId_sale());
            saleDetailsDTO.setDate(sale.getDate());
            saleDetailsDTO.setBuys_id(buysProductsDTO.getId_buys());
            saleDetailsDTO.setTotal_price(buysProductsDTO.getTotal_price());
            saleDetailsDTO.setProductDTO(buysProductsDTO.getProductsList());

            return saleDetailsDTO;
        } else {
            // Manejar el caso donde la solicitud no fue exitosa
            throw new FeignRequestException("Error fetching purchase details from Feign: " + responseEntity.getStatusCode());
        }

    }

}

