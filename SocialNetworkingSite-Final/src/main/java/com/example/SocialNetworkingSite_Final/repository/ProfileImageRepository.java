package com.example.SocialNetworkingSite_Final.repository;

import com.example.SocialNetworkingSite_Final.model.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long> {
    ProfileImage findByUserId(long id);
}