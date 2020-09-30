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
//        return new User("freddie", "queen", new ArrayList<>());
        Optional<User> user = userRepository.findByUsername(userName);
        return user.orElseThrow(
                () -> new UsernameNotFoundException("Not found: " + userName)
        );
    }
}