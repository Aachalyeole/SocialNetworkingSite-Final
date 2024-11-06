package com.example.SocialNetworkingSite_Final.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Album {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long aid;
    private String aName;
    private boolean privacy;
    private byte coverImage;
    private Date aDate;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Gallery> gallery;
}
