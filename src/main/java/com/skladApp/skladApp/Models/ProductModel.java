package com.skladApp.skladApp.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
@Data
public class ProductModel {
    @Id
    private String id;
    private String Name;
    private String Description;
    private String Type;
    private int Count;
}
