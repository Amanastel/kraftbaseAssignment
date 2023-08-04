package com.kraftbase.service.impl;

import com.kraftbase.exceptions.ProductException;
import com.kraftbase.model.Availability;
import com.kraftbase.model.Product;
import com.kraftbase.repository.ProductRepo;
import com.kraftbase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepository;
    @Override
    public Product addProduct(Product product) {
        if (product == null)
            throw new ProductException("Invalid product details");

        product.setAvailability(Availability.AVAILABLE);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductException("Product not found with id: " + id));
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        Product product1 = productRepository.findById(id).orElseThrow(() -> new ProductException("Product not found with id: " + id));
        product1.setProductName(product.getProductName());
        product1.setProductPrice(product.getProductPrice());
        product1.setProductQuantity(product.getProductQuantity());
        return productRepository.save(product1);
    }

    @Override
    public String changeAvailability(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductException("Product not found with id: " + id));
        if (product.getAvailability() == Availability.AVAILABLE) {
            product.setAvailability(Availability.NOT_AVAILABLE);
            productRepository.save(product);
            return "Product is not available";
        } else {
            product.setAvailability(Availability.AVAILABLE);
            productRepository.save(product);
            return "Product is available";
        }
    }


    @Override
    public Product deleteProduct(Integer id) {
        Product p = productRepository.findById(id).orElseThrow(() -> new ProductException("Product not found with id: " + id));
        productRepository.delete(p);
        return p;
    }

    @Override
    public Product addQuantity(Integer id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductException("Product not found with id: " + id));
        product.setProductQuantity(product.getProductQuantity() + quantity);
        return productRepository.save(product);
    }

    @Override
    public Product removeQuantity(Integer id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductException("Product not found with id: " + id));
        product.setProductQuantity(product.getProductQuantity() - quantity);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductByName(String name) {
        List<Product> products = productRepository.findByProductName(name);
        if (products.isEmpty())
            throw new ProductException("Product not found with name: " + name);
        return products;
    }



    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty())
            throw new ProductException("No products found");
        return products;
    }
}
