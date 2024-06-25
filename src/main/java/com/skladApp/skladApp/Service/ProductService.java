package com.skladApp.skladApp.Service;

import com.skladApp.skladApp.Models.ProductModel;
import com.skladApp.skladApp.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository repository;

    public List<ProductModel> allProducts(){
        return repository.findAll();
    }

    public void addProduct(ProductModel product){
        repository.insert(product);
    }

    public void updateProduct(ProductModel product){
        repository.save(product);
    }

    public void deleteProduct(ProductModel product){
        repository.delete(product);
    }

    public List<ProductModel> productsByName(String name){
        return allProducts().stream()
                .filter(productModel -> productModel.getName().contains(name))
                .toList();
    }

    public List<ProductModel> productsByType(String type){
        return allProducts().stream()
                .filter(productModel -> productModel.getType().equals(type))
                .toList();
    }

    public List<ProductModel> productEnded(){
        return allProducts().stream()
                .filter(productModel -> productModel.getCount() == 0)
                .toList();
    }

    public List<ProductModel> productNotEnded(){
        return allProducts().stream()
                .filter(productModel -> productModel.getCount() != 0)
                .toList();
    }
}
