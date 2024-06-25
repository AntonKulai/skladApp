package com.skladApp.skladApp.Controllers;

import com.skladApp.skladApp.Models.UserModel;
import com.skladApp.skladApp.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService service;

    @GetMapping("/all-users")
    public List<UserModel> allUsers(){
        return service.allUsers();
    }
    @PostMapping("/add-user")
    public void addUser(@RequestBody UserModel user){
        service.addUser(user);
    }
    @PutMapping("/update-user")
    public void updateUser(@RequestBody UserModel user){
        service.updateUser(user);
    }
    @DeleteMapping("/delete-user")
    public void deleteUser(@RequestBody UserModel user){
        service.deleteUser(user);
    }
}
