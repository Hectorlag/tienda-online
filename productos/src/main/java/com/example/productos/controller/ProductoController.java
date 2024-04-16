package com.example.productos.controller;

import com.example.productos.model.Product;
import com.example.productos.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductoController {

    @Autowired
    private IProductService proServi;

    @GetMapping("/get")
    public List<Product> getProducts(){

        return proServi.getProducts();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@Valid @RequestBody Product product,
                                           BindingResult bindingResult){
        // Handle validation errors
        if (bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("Validation error: " + bindingResult.getAllErrors());
        }

        proServi.saveProduct(product);
        return ResponseEntity.ok("successfully created product");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> editProduct(@PathVariable Long id, @Valid
                                              @RequestBody Product product,
                                              BindingResult bindingResult){
        //Handle validation errors
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(("Validation error: " + bindingResult.getAllErrors()));
        }
        //Update in the database
        proServi.editProduct(id, product);
        return ResponseEntity.ok("successfully edit product");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        //Handle validation errors
        if(id == null){
            return ResponseEntity.badRequest().body("The id cannot be null" );
        }
        proServi.deleteProduct(id);
        return ResponseEntity.ok("Successfully deleted product");
    }

    @GetMapping("/get/{id}")
    public Product getProductById(@PathVariable Long id){

        if(id == null){
            throw new IllegalArgumentException("Product ID cannot be null");
        }

       return proServi.findProduct(id);
    }
}
