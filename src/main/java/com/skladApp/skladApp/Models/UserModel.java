package com.skladApp.skladApp.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    @Id
    private String id;
    private String Login;
    private String Password;
}
