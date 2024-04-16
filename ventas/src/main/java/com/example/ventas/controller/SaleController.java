package com.example.ventas.controller;

import com.example.ventas.DTO.SaleDetailsDTO;
import com.example.ventas.model.Sale;
import com.example.ventas.service.ISaleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @GetMapping("/get")
    public List<Sale> getSales(){

        return saleService.getSales();
    }

    @PostMapping("/create/{idBuys}")
    public ResponseEntity<?> createSales(@PathVariable Long idBuys){

        if(idBuys == null){

            return ResponseEntity.badRequest().body("Validation error");
        }else{
            saleService.createSale(idBuys);
            return ResponseEntity.ok("successfully created sales");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editSales(@PathVariable Long idSales, @Valid
                                            @RequestBody Sale sale,
                                            BindingResult bindingResult){

        //Handle validation error
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Validation error" + bindingResult.getAllErrors());
        }else {

         saleService.editSale(idSales, sale);
         return ResponseEntity.ok("Successfully edit sales");
        }


    }

    @DeleteMapping("/delete/{idSales}")
    public ResponseEntity<?> deleteSales(@PathVariable Long idSales){

        if(idSales == null){
            return ResponseEntity.badRequest().body("The id cannot be null");
        }
        else {
            saleService.deleteSale(idSales);
            return ResponseEntity.ok("Sucessfully delete sales");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSalesById(@PathVariable Long id) {

        if (id == null) {
            return ResponseEntity.badRequest().body("The id cannot be null");
        } else {
            Sale sale = saleService.findSale(id);
            if (sale == null) {
                return ResponseEntity.badRequest().body("Purchase not found ");
            } else {
                return ResponseEntity.ok(sale);
            }
        }
    }

    @GetMapping("/getwithbuys/{sales_id}")
    public ResponseEntity<?> getSalesAndBuys(@PathVariable Long sales_id){

       if(sales_id == null){
           return ResponseEntity.badRequest().body("The id cannot be null");
       }   else {
           SaleDetailsDTO sale = saleService.findSalewithBuys(sales_id);
           if (sale == null) {
               return ResponseEntity.badRequest().body("Purchase not found ");
           } else {
               return ResponseEntity.ok(sale);
           }
       }

    }






}
