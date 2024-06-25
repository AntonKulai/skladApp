package com.skladApp.skladApp;

import com.skladApp.skladApp.Models.ProductModel;
import com.skladApp.skladApp.Repository.ProductRepository;
import com.skladApp.skladApp.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllProducts() {
        List<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel("1", "Product1", "Description1", "Type1", 10));
        products.add(new ProductModel("2", "Product2", "Description2", "Type2", 5));

        when(productRepository.findAll()).thenReturn(products);

        List<ProductModel> result = productService.allProducts();
        assertEquals(2, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testAddProduct() {
        ProductModel product = new ProductModel("3", "Product3", "Description3", "Type3", 15);

        productService.addProduct(product);

        verify(productRepository, times(1)).insert(product);
    }

    @Test
    void testUpdateProduct() {
        ProductModel product = new ProductModel("4", "Product4", "Description4", "Type4", 20);

        productService.updateProduct(product);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        ProductModel product = new ProductModel("5", "Product5", "Description5", "Type5", 0);

        productService.deleteProduct(product);

        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testProductsByName() {
        List<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel("1", "ProductA", "DescriptionA", "TypeA", 10));
        products.add(new ProductModel("2", "ProductB", "DescriptionB", "TypeB", 5));

        when(productRepository.findAll()).thenReturn(products);

        List<ProductModel> result = productService.productsByName("ProductA");
        assertEquals(1, result.size());
        assertEquals("ProductA", result.get(0).getName());
    }

    @Test
    void testProductsByType() {
        List<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel("1", "ProductA", "DescriptionA", "TypeA", 10));
        products.add(new ProductModel("2", "ProductB", "DescriptionB", "TypeB", 5));

        when(productRepository.findAll()).thenReturn(products);

        List<ProductModel> result = productService.productsByType("TypeA");
        assertEquals(1, result.size());
        assertEquals("TypeA", result.get(0).getType());
    }

    @Test
    void testProductEnded() {
        List<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel("1", "ProductA", "DescriptionA", "TypeA", 0));
        products.add(new ProductModel("2", "ProductB", "DescriptionB", "TypeB", 5));

        when(productRepository.findAll()).thenReturn(products);

        List<ProductModel> result = productService.productEnded();
        assertEquals(1, result.size());
        assertEquals(0, result.get(0).getCount());
    }

    @Test
    void testProductNotEnded() {
        List<ProductModel> products = new ArrayList<>();
        products.add(new ProductModel("1", "ProductA", "DescriptionA", "TypeA", 10));
        products.add(new ProductModel("2", "ProductB", "DescriptionB", "TypeB", 5));
        products.add(new ProductModel("3", "ProductC", "DescriptionC", "TypeC", 0));

        when(productRepository.findAll()).thenReturn(products);

        List<ProductModel> result = productService.productNotEnded();
        assertEquals(2, result.size());
        assertNotEquals(0, result.get(0).getCount());
        assertNotEquals(0, result.get(1).getCount());
    }
}
