package com.example.SocialNetworkingSite_Final.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.SocialNetworkingSite_Final.model.ProfileImage;
import com.example.SocialNetworkingSite_Final.model.User;
import com.example.SocialNetworkingSite_Final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public UserController() {
    }

    @PostMapping({"/{id}/uploadProfileImage"})
    public ResponseEntity<String> uploadProfileImage(@PathVariable int id, @RequestParam("file") MultipartFile file) {
        try {
            this.userService.saveProfileImage((long)id, file);
            return ResponseEntity.ok("Profile image uploaded successfully");
        } catch (IOException var4) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile image");
        }
    }

    @GetMapping({"/{id}/profileImage"})
    public ResponseEntity<byte[]> getProfileImage(@PathVariable long id) {
        ProfileImage profileImage = this.userService.getProfileImage(id);
        return profileImage != null && profileImage.getProfileImage() != null ? ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(profileImage.getProfileImage()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body((byte[]) null);
    }

    @GetMapping({"allUsers"})
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = this.userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping({"/{id}/userSuggestions"})
    public ResponseEntity<List<User>> getSuggestions(@PathVariable long id) {
        List<User> users = this.userService.getAllUser();
        List<User> filteredUsers = new ArrayList();
        Iterator var5 = users.iterator();

        while(var5.hasNext()) {
            User user = (User)var5.next();
            if (user.getUid() != id) {
                filteredUsers.add(user);
            }
        }

        return ResponseEntity.ok(filteredUsers);
    }
}
