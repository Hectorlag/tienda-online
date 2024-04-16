package com.example.compras.service;

import com.example.compras.DTO.BuysProductsDTO;
import com.example.compras.DTO.ProductDTO;
import com.example.compras.excepcion.BuysNotFoundExcepcion;
import com.example.compras.excepcion.ProductNotFoundExcepcion;
import com.example.compras.model.Buys;
import com.example.compras.repository.IBuysRepository;
import com.example.compras.repository.IProductFeignClient;
import com.example.compras.repository.ISaleFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuysService implements IBuysService{

    // Sales service
    @Autowired
    private IProductFeignClient productFeignClient;

    // Buys service
    @Autowired
    private ISaleFeignClient saleFeignClient;

    @Autowired
    private IBuysRepository buysRepo;
    @Override
    public List<Buys> getBuys() {

        return  buysRepo.findByDeletedFalse();
    }

    @Override
    @CircuitBreaker(name = "productos", fallbackMethod = "fallBackGetProducts")
    @Retry(name = "productos")
    public BuysProductsDTO create(List<Long> idsProducts) {

        // I create a list of productsIds
        List<Long> productsIdsList = new ArrayList<>();

        // I create a list of productDTO
        List<ProductDTO> listProductDTO = new ArrayList<>();

        // Initialize total_price to 0.0
        double totalPrice = 0.0;

        for(Long id: idsProducts){
            // I use Openfeing
            ProductDTO p = productFeignClient.getProductById(id);
            if(p == null){
                throw new ProductNotFoundExcepcion("Product with id: " + id + " not found");
            }
            // I save the product ID in the ID list
            productsIdsList.add(p.getId());

            listProductDTO.add(p);

            // Add the price of the product to the total_price
            totalPrice  += p.getPrice();
        }
        //I save in the database
        Buys buys = new Buys();
        buys.setTotal_price(totalPrice);
        buys.setProductsIdsList(productsIdsList);

        // Save and GET the object with assigned ID
        buys = buysRepo.save(buys);

        // I send the buys id to the sales service
        saleFeignClient.createSales(buys.getId_buys());

        // Create the BuysProductsDTO object with the purchase details
        BuysProductsDTO buysProductsDTO = new BuysProductsDTO();
        buysProductsDTO.setId_buys(buys.getId_buys());
        buysProductsDTO.setTotal_price(buys.getTotal_price());
        buysProductsDTO.setProductsList(listProductDTO);

        // I return the purchase with the details
        return buysProductsDTO;
    }
    public BuysProductsDTO fallBackGetProducts(Throwable throwable){

        return new BuysProductsDTO(9999999999L,99999999999.00, null);
    }

    @Override
    public void editBuys(Long id, Buys buys) {

        Buys b = this.find(id);
        b.setId_buys(buys.getId_buys());
        b.setTotal_price(buys.getTotal_price());
        b.setProductsIdsList(buys.getProductsIdsList());

        buysRepo.save(b);
    }

    //Delete
    @Override
    public void deleteBuys(Long id) {

        Buys b = this.find(id);
        b.setDeleted(true);

        buysRepo.save(b);
    }

    // I get buys with of details
    @Override
    public BuysProductsDTO findBuysAndDetails(Long id) {

         Buys buys = buysRepo.findById(id).orElse(null);
         if(buys == null){
             throw new BuysNotFoundExcepcion("No buys found with the id: " + id);
         }

         List<Long> idsList = buys.getProductsIdsList();

        // I use Feign to get the details of each product
         List<ProductDTO> productsDTO = new ArrayList<>();
         for(Long productId: idsList){
             ProductDTO p = productFeignClient.getProductById(productId.longValue());
             productsDTO.add(p);
         }

        // I build the DTO with the details of buys
        BuysProductsDTO buysProductsDTO = new BuysProductsDTO();
         buysProductsDTO.setId_buys(buys.getId_buys());
         buysProductsDTO.setTotal_price(buys.getTotal_price());
         buysProductsDTO.setProductsList(productsDTO);

         return buysProductsDTO;
    }


    //I only return the purchase
    @Override
    public Buys find(Long id) {

        Buys b = buysRepo.findById(id).orElse(null);
        if( b == null){
            throw  new BuysNotFoundExcepcion("No buys found with the id " + id);
        }
        return b;
    }


}
