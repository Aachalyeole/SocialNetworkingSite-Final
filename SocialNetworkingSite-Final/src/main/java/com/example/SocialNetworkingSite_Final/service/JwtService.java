package com.example.SocialNetworkingSite_Final.service;


import com.example.SocialNetworkingSite_Final.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private final String Private_Key = "4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c";

    public JwtService() {
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = this.extractAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token) {
        return (String)this.extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails user) {
        String username = this.extractUsername(token);
        return username.equals(user.getUsername()) && !this.isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return (Date)this.extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return (Claims)Jwts.parserBuilder().setSigningKey(this.getSigninKey()).build().parseClaimsJws(token).getBody();
    }

    public String generateToken(User user) {
        String token = Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 3600000L)).signWith(this.getSigninKey()).compact();
        return token;
    }

    private Key getSigninKey() {
        byte[] keyBytes = (byte[])Decoders.BASE64URL.decode("4bb6d1dfbafb64a681139d1586b6f1160d18159afd57c8c79136d7490630407c");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}