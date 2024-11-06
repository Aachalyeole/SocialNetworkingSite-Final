package com.example.SocialNetworkingSite_Final.repository;

import com.example.SocialNetworkingSite_Final.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}