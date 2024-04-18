package com.example.compras.controller;

import com.example.compras.DTO.BuysProductsDTO;
import com.example.compras.model.Buys;
import com.example.compras.service.IBuysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buys")
public class BuysController {

    @Autowired
    private IBuysService buysServi;

    @Value("${server.port}")
    private int serverPort;

    @GetMapping("/get")
    public List<Buys> getBuys(){

        return buysServi.getBuys();
    }

    @PostMapping("/create")
    public BuysProductsDTO addProducts(@RequestBody List<Long> idsProducts) {


      /*  if(idsProducts == null || idsProducts.isEmpty()){

            return ResponseEntity.badRequest().body("Debe seleccionar algún producto");
        }*/
        return buysServi.create(idsProducts);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editBuys(@PathVariable Long id,
                                           @RequestBody Buys buys){
        if(buys == null || id == null){
             return ResponseEntity.badRequest().body("id or purchase cannot be null");
        }
        buysServi.editBuys(id, buys);
        return ResponseEntity.ok("successfully edit buys");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBuys(@PathVariable Long id){

        if(id == null){
            return ResponseEntity.badRequest().body("The id cannot be null");
        }
        buysServi.deleteBuys(id);
        return ResponseEntity.ok("successfully deleted buys");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> find(@PathVariable Long id){

        if(id == null){
            return ResponseEntity.badRequest().body("The id cannot be null");
        }
        Buys b = buysServi.find(id);
        return ResponseEntity.ok(b);
    }



    // I get buys and details
    @GetMapping("/getdetails/{id}")
    public ResponseEntity<?> findBuysById(@PathVariable Long id){

        System.out.println("Estoy en el puerto: " + serverPort );

        if(id == null){
            return ResponseEntity.badRequest().body("The id cannot be null");
        }
        BuysProductsDTO b = buysServi.findBuysAndDetails(id);
        return ResponseEntity.ok(b);
    }
}
