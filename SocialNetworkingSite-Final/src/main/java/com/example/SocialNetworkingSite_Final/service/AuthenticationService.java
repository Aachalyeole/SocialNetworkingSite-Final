package com.example.SocialNetworkingSite_Final.service;


import com.example.SocialNetworkingSite_Final.dto.LoginDTO;
import com.example.SocialNetworkingSite_Final.dto.SignupDTO;
import com.example.SocialNetworkingSite_Final.model.AuthenticationResponse;
import com.example.SocialNetworkingSite_Final.model.User;
import com.example.SocialNetworkingSite_Final.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(LoginDTO request) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = this.userRepo.findByEmail(request.getEmail());
        String token = this.jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    public SignupDTO register(User request) {
        if (this.userRepo.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("User with this email already exists");
        } else {
            User user = new User();
            user.setUserName(request.getUsername());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setPassword(this.passwordEncoder.encode(request.getPassword()));
            user.setEmail(request.getEmail());
            user.setMobile(request.getMobile());
            user.setBirthDate(request.getBirthDate());
            user.setCity(request.getCity());
            User savedUser = (User)this.userRepo.save(user);
            return new SignupDTO(savedUser.getUsername(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail(), savedUser.getMobile(), savedUser.getBirthDate(), savedUser.getCity(), savedUser.getPassword());
        }
    }
}
