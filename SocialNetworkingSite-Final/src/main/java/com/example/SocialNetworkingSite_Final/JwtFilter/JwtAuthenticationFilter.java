package com.example.SocialNetworkingSite_Final.JwtFilter;


import com.example.SocialNetworkingSite_Final.service.JwtService;
import com.example.SocialNetworkingSite_Final.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter {
    private final JwtService jwtService;
    private final UserService userService;

    public JwtAuthenticationFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request == null) {
            throw new NullPointerException("request is marked non-null but is null");
        } else if (response == null) {
            throw new NullPointerException("response is marked non-null but is null");
        } else if (filterChain == null) {
            throw new NullPointerException("filterChain is marked non-null but is null");
        } else {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String username = this.jwtService.extractUsername(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userService.loadUserByUsername(username);
                    if (this.jwtService.isValid(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, (Object)null, userDetails.getAuthorities());
                        authToken.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }

                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
