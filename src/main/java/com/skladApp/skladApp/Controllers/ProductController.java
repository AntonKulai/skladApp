package com.skladApp.skladApp.Controllers;

import com.skladApp.skladApp.Models.ProductModel;
import com.skladApp.skladApp.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private ProductService service;

    @GetMapping("/all-products")
    public List<ProductModel> allProducts(){
        return service.allProducts();
    }
    @PostMapping("/add-product")
    public void addProduct(ProductModel product){
        service.addProduct(product);
    }
    @PutMapping("/update-product")
    public void updateProduct(ProductModel product){
        service.updateProduct(product);
    }
    @DeleteMapping("/delete-mapping")
    public void deleteProduct(ProductModel product){
        service.deleteProduct(product);
    }
    @PostMapping("/products-by-name/{name}")
    public List<ProductModel> productsByName(@PathVariable String name){
        return service.productsByName(name);
    }
    @PostMapping("/products-by-type/{type}")
    public List<ProductModel> productsByType(@PathVariable String type){
        return service.productsByType(type);
    }
    @GetMapping("/products-ended")
    public List<ProductModel> productEnded(){
        return service.productEnded();
    }
    @GetMapping("/products-not-ended")
    public List<ProductModel> productNotEnded(){
        return service.productNotEnded();
    }
}
