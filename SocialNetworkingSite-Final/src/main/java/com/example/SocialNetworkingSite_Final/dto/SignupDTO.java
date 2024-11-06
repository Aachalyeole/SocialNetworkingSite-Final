package com.example.SocialNetworkingSite_Final.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private long mobile;
    private Date birthDate;
    private String city;
    private String password;
}
