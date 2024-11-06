package com.example.SocialNetworkingSite_Final.service;


import java.io.IOException;
import java.util.List;

import com.example.SocialNetworkingSite_Final.model.ProfileImage;
import com.example.SocialNetworkingSite_Final.model.User;
import com.example.SocialNetworkingSite_Final.repository.ProfileImageRepository;
import com.example.SocialNetworkingSite_Final.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final ProfileImageRepository profileImageRepository;

    public UserService(UserRepository userRepository, ProfileImageRepository profileImageRepository) {
        this.userRepository = userRepository;
        this.profileImageRepository = profileImageRepository;
    }

    public void saveProfileImage(long id, MultipartFile file) throws IOException {
        this.userRepository.findById(id);
        ProfileImage profileImage = this.profileImageRepository.findByUserId(id);
        if (profileImage == null) {
            profileImage = new ProfileImage();
            profileImage.setUserId(id);
        }

        profileImage.setProfileImage(file.getBytes());
        this.profileImageRepository.save(profileImage);
    }

    public ProfileImage getProfileImage(long userId) {
        return this.profileImageRepository.findByUserId(userId);
    }

    public List<User> getAllUser() {
        return this.userRepository.findAll();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(username);
    }
}