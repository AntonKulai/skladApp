package com.skladApp.skladApp.Service;

import com.skladApp.skladApp.Models.UserModel;
import com.skladApp.skladApp.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
@Primary
public class UserService implements UserDetailsService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public List<UserModel> allUsers(){
        return repository.findAll();
    }

    public void addUser(UserModel user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.insert(user);
    }

    public void updateUser(UserModel user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public void deleteUser(UserModel user){
        repository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var user = allUsers().stream()
                .filter(userModel -> userModel.getLogin().equals(login))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
