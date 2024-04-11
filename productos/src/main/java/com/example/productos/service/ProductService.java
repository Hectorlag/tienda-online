package com.example.productos.service;

import com.example.productos.excepcion.ProductNotFoundException;
import com.example.productos.model.Product;
import com.example.productos.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private IProductRepository proRepo;

    @Override
    public List<Product> getProducts() {

        return proRepo.findAll();
    }

    @Override
    public void saveProduct(Product p) {

        proRepo.save(p);
    }

    @Override
    public void editProduct(Long id, Product p) {

        //I'm looking for the product
         Product product = this.findProduct(id);
         //set the data
         product.setId(p.getId());
         product.setName(p.getName());
         product.setBrand(p.getBrand());
         product.setPrice(p.getPrice());

         proRepo.save(product);
    }

    @Override
    public void deleteProduct(Long id) {

        //I'm looking for the product
        Product product = this.findProduct(id);
        product.setDeleted(true);

        proRepo.save(product);

    }

    @Override
    public Product findProduct(Long id) {

        Product product = proRepo.findById(id).orElse(null);

        if(product == null){
            throw new ProductNotFoundException("No product found with the id: " + id);
        }

        return product;
    }
}
