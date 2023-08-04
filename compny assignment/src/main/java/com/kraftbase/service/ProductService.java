package com.kraftbase.service;

import com.kraftbase.model.Product;

import java.util.List;

public interface ProductService {

    public Product addProduct(Product product);
    public Product getProductById(Integer id);
    public Product updateProduct(Integer id,Product product);
    public String changeAvailability(Integer id);
    public Product deleteProduct(Integer id);
    public Product addQuantity(Integer id,int quantity);
    public Product removeQuantity(Integer id,int quantity);
    public List<Product> getProductByName(String name);
    public List<Product> getAllProducts();

}
