package com.example.springsecurityexample;

import com.example.springsecurityexample.model.Role;
import com.example.springsecurityexample.model.User;
import com.example.springsecurityexample.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSignUpService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void registerANewUser(String username, String password, String email) throws Exception {
        if (this.isUserNameAvailable(username) && this.isEmailAvailable(email)) {
            User user = new User(
                    username,
                    passwordEncoder.encode(password),
                    email,
                    true,
                    Arrays.asList(Role.getUserRol()));
            userRepository.save(user);
        }
    }

    private boolean isUserNameAvailable(String userName) {
        Optional<User> user = userRepository.findByUsername(userName);
        return user.isEmpty();
    }

    private boolean isEmailAvailable(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isEmpty();
    }

}
