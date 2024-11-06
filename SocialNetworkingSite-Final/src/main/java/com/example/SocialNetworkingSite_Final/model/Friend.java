package com.example.SocialNetworkingSite_Final.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Friend {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long fid;
    private long f_Uid;
    private long to_Uid;
    private boolean status;
    private Date date;
}
