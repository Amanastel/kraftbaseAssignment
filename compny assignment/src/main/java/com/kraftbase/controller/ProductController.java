package com.kraftbase.controller;

import com.kraftbase.model.Product;
import com.kraftbase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    /*
    {

        "productName": "Maggi",
        "productPrice": 12.0,
        "productQuantity": 10
    }
     */

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProductHandler(@RequestBody Product product){
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProductHandler(@PathVariable Integer id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("changeAvailability/{id}")
    public ResponseEntity<String> changeAvailabilityHandler(@PathVariable Integer id){
        return ResponseEntity.ok(productService.changeAvailability(id));
    }

    @GetMapping("/deleteProduct/{id}")
    public ResponseEntity<Product> deleteProductHandler(@PathVariable Integer id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/addQuantity/{id}/{quantity}")
    public ResponseEntity<Product> addQuantityHandler(@PathVariable Integer id,@PathVariable Integer quantity){
        return ResponseEntity.ok(productService.addQuantity(id,quantity));
    }

    @GetMapping("/removeQuantity/{id}/{quantity}")
    public ResponseEntity<Product> removeQuantityHandler(@PathVariable Integer id,@PathVariable Integer quantity){
        return ResponseEntity.ok(productService.removeQuantity(id,quantity));
    }

    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<List<Product>> getProductByNameHandler(@PathVariable String name){
        return ResponseEntity.ok(productService.getProductByName(name));
    }
    @PatchMapping("/updateProduct/{id}")
    public ResponseEntity<Product> getProductHandler(@PathVariable Integer id, @RequestBody Product product){
        return ResponseEntity.ok(productService.updateProduct(id,product));

    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProductsHandler(){
        return ResponseEntity.ok(productService.getAllProducts());
    }


}
