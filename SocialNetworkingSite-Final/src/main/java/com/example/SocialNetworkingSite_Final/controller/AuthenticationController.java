package com.example.SocialNetworkingSite_Final.controller;


import com.example.SocialNetworkingSite_Final.dto.LoginDTO;
import com.example.SocialNetworkingSite_Final.model.AuthenticationResponse;
import com.example.SocialNetworkingSite_Final.model.User;
import com.example.SocialNetworkingSite_Final.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping({"/login"})
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginDTO request) {
        return ResponseEntity.ok(this.service.authenticate(request));
    }

    @PostMapping({"/register"})
    public HttpStatus register(@RequestBody User user) {
        this.service.register(user);
        return (HttpStatus)ResponseEntity.ok(HttpStatus.CREATED).getBody();
    }
}
