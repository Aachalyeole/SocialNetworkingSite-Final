package com.example.SocialNetworkingSite_Final.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.sql.Date;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Gallery {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long gid;
    @ManyToOne
    private Album album;
    private byte image;
    private String description;
    private Date gDate;

}
