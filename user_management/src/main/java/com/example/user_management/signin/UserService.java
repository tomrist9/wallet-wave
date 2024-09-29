package com.example.user_management.signin;


import com.example.user_management.registration.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.user_management.registration.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User signIn(String username, String password) {


        User user = userRepository.findById(userId).orElse(null);



        if (user != null && passwordEncoder.matches(password, user.getPassword())) {

            return user;
        } else {

            return null;
        }
    }
}
