package com.example.SocialNetworkingSite_Final.service;


import com.example.SocialNetworkingSite_Final.model.FriendRequest;
import com.example.SocialNetworkingSite_Final.model.User;
import com.example.SocialNetworkingSite_Final.repository.FriendRequestRepository;
import com.example.SocialNetworkingSite_Final.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestService {
    public static final String PENDING = "PENDING";
    public static final String ACCEPTED = "ACCEPTED";
    public static final String REJECTED = "REJECTED";
    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;

    public FriendRequestService(UserService userService, UserRepository userRepository, FriendRequestRepository friendRequestRepository) {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
    }

    public FriendRequest sendFriendRequest(long senderId, long receiverId) {
        User sender = (User)this.userRepository.findById(senderId).get();
        User receiver = (User)this.userRepository.findById(receiverId).get();
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setStatus("PENDING");
        return (FriendRequest)this.friendRequestRepository.save(friendRequest);
    }
}