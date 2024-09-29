package com.example.user_management.signin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendPasswordResetEmail(String userEmail, String resetLink) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject("Password Reset");
        message.setText("Click the following link to reset your password: " + resetLink);
        javaMailSender.send(message);
    }
}