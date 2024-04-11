package com.example.productos.service;

import com.example.productos.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getProducts();

    public void saveProduct(Product p);

    public void editProduct(Long id, Product p);

    public void deleteProduct(Long id);
    public Product findProduct(Long id);

}
