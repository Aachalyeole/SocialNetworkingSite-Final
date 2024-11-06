package com.example.SocialNetworkingSite_Final.controller;

import com.example.SocialNetworkingSite_Final.service.FriendRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/friend-request"})
public class FriendRequestController {
    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping({"/send"})
    public ResponseEntity<String> sendFriendRequest(@RequestParam long senderId, @RequestParam long receiverId) {
        this.friendRequestService.sendFriendRequest(senderId, receiverId);
        return ResponseEntity.ok("Request Send");
    }
}
