package com.skladApp.skladApp.Repository;

import com.skladApp.skladApp.Models.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {
}
