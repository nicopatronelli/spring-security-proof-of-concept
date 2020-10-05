package com.example.springsecurityexample;

import com.example.springsecurityexample.model.User;
import com.example.springsecurityexample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        if (userName.equals("freddie"))
//            return new User("freedie", "queen", new ArrayList<>());
//        else throw new UsernameNotFoundException("");
        Optional<User> user = userRepository.findByUsername(userName);
        User us = user.get();

        System.out.println("*****************************************************");
        System.out.println("The username recovered from database is: " + us.getUsername());
        System.out.println("The password recovered from database is: " + us.getPassword());
        System.out.println("The password recovered from database is: " + us.getAuthorities().toString());
        System.out.println(user.get());
        return user.orElseThrow(
                () -> {
                    System.out.println("The UsernameNotFoundException was thrown");
                    return new UsernameNotFoundException("Not found: " + userName);
                }
        );
    }

}