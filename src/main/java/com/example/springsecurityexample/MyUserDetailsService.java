package com.example.springsecurityexample;

import com.example.springsecurityexample.model.Role;
import com.example.springsecurityexample.model.User;
import com.example.springsecurityexample.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("**** Database impact from loadUserByUserName ocurred ****");
        Optional<User> user = userRepository.findByUsername(userName);
        return user.orElseThrow(
                    () -> new UsernameNotFoundException("Not found: " + userName)
        );
    }

    private boolean isUserNameAvailable(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        return user.isEmpty();
    }

    public void registerANewUser(String username, String password) throws Exception {
        if (isUserNameAvailable(username)) {
            User user = new User(username, password, true, Arrays.asList(Role.getUserRol()));
            userRepository.save(user);
        } else throw new Exception("The username is not available");
    }
}