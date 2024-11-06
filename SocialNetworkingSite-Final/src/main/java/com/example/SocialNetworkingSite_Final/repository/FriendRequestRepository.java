package com.example.SocialNetworkingSite_Final.repository;

import com.example.SocialNetworkingSite_Final.model.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
}
