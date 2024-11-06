package com.example.SocialNetworkingSite_Final.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import lombok.Generated;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long uid;
    @Column(
            unique = true
    )
    private String userName;
    private String firstName;
    private String lastName;
    @Column(
            unique = true
    )
    private String email;
    @Column(
            unique = true
    )
    private long mobile;
    private Date birthDate;
    private String city;
    private String password;
    private String education;
    private int pincode;
    private String gender;
    private String website;
    private String aboutUs;
    @OneToMany
    private List<Album> album;
    @OneToMany
    private List<Post> post;
    @OneToMany
    private Set<User> friend;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getUsername() {
        return this.getEmail();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}
