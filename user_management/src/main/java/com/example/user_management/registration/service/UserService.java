package com.example.user_management.registration.service;

import com.example.user_management.registration.dto.UserRegistrationDTO;
import com.example.user_management.registration.entity.User;
import com.example.user_management.registration.repository.UserRepository;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public void registerUser(UserRegistrationDTO registrationDTO) throws Exception {
        validateRegistrationData(registrationDTO);

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(encodePassword(registrationDTO.getPassword()));
        user.setEmail(registrationDTO.getEmail());
        userRepository.save(user);

        String confirmationEmail = generateConfirmationLink(user);
        emailService.sendConfirmationLink(user, confirmationEmail);}

    private void validateRegistrationData(UserRegistrationDTO registrationDTO) throws Exception {
        if (registrationDTO.getUsername() == null || registrationDTO.getUsername().trim().isEmpty()) {
            throw new Exception("Invalid username");
        }
        if (!EmailValidator.getInstance().isValid(registrationDTO.getEmail())) {
            throw new Exception("Invalid e-mail address");
        }
        if (registrationDTO.getPassword().length() < 8) {
            throw new Exception("Password must be at least 8 characters long.");
        }
        if (!passwordMeetsComplexityRequirements(registrationDTO.getPassword())) {
            throw new Exception("Password must contain at least one uppercase letter, one number, and one special character.");
        }
    }

    private boolean passwordMeetsComplexityRequirements(String password) {
        // Check for at least one uppercase letter, one number, and one special character
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-_=+\\[\\]{};:'\",<.>/?`~]).+$";
        return password.matches(regex);
    }

    private String encodePassword(String password) {
        // Implement password encoding logic using BCryptPasswordEncoder
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    private String generateConfirmationLink(User user) {

        String confirmationToken = UUID.randomUUID().toString();


       String confirmationLink = "http://yourdomain.com/user/confirm?token=" + confirmationToken;


        return confirmationLink;
    }
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }
    public User createUser(User newUser){
        return userRepository.save(newUser);
    }
    public User updateUser(Long userId, User user){
        User existingUser= userRepository.findById(userId)
                .orElseThrow(()->new UsernameNotFoundException("User not found with id: " + userId));

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }
    public void  deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}

