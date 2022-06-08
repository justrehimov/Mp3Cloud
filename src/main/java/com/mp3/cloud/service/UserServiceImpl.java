package com.mp3.cloud.service;

import com.mp3.cloud.entity.User;
import com.mp3.cloud.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService {
   private final UserRepo userRepo;
   private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found"));
    }

    public String register(String name, String surname, String username, String password) {
        Optional<User> user = userRepo.findByUsername(username);
        if(user.isPresent())
            return "register?error";
        User newUser = new User();
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        User saved = userRepo.save(newUser);
        if(saved.getId() != null)
            return "login";
        return "register?regerror";
    }
}
