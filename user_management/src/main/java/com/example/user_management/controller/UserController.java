package com.example.user_management.controller;

import com.example.user_management.registration.entity.User;
import com.example.user_management.registration.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "This endpoint helps us to sign up",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The request was successful"),
                    @ApiResponse(responseCode = "400", description = "There is incoming request validation error"),
                    @ApiResponse(responseCode = "409", description = "There is a conflict with the current state of the resource, preventing the request from being completed."),
                    @ApiResponse(responseCode = "417", description = "The server cannot meet the expectations specified in the request"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server.")
            })
    public JWTToken signUp(@RequestBody @Valid UserSignInRequest userSignInRequest) {
        return userService.signIn(userSignInRequest);
    }

    @PostMapping("/recover")
    @Operation(summary = "This endpoint helps us to recover your sccount",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The request was successful"),
                    @ApiResponse(responseCode = "400", description = "There is incoming request validation error"),
                    @ApiResponse(responseCode = "409", description = "There is a conflict with the current state of the resource, preventing the request from being completed."),
                    @ApiResponse(responseCode = "417", description = "The server cannot meet the expectations specified in the request"),
                    @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server.")
            })

    public void sendOTP(@RequestBody @Valid UserRecoverAccountRequest userRecoverAccountRequest) {
        userService.sendOTP(userRecoverAccountRequest);
    }

    @PostMapping("/recover/otp")
    public void recover(@RequestBody @Valid UserRecoverAccountOTPRequest userRecoverAccountOTPRequest) {
        userService.recover(userRecoverAccountOTPRequest);

    }

}