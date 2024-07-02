package com.example.uade.tpo.Configuration.auth;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import com.example.uade.tpo.entity.Investigator;
import com.example.uade.tpo.entity.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtService {
    @Value("${application.security.jwt.secretKey}")
    private String secretKey;
    @Value("${application.security.jwt.expirationTime}")
    private long jwtExpiration;

    public String generateToken(UserDetails userDetails) {
        if( userDetails instanceof User) {
            User user = (User) userDetails;
            return buildToken(user.getUsername(), user.getRole().name(), jwtExpiration);
        } else if (userDetails instanceof Investigator) {
            Investigator investigator = (Investigator) userDetails;
            return buildToken(investigator.getUsername(), investigator.getRole().name(), jwtExpiration);
        } else {
            throw  new IllegalArgumentException("UserDetails must be an instance of User or Investigator");
        }
    }

    private String buildToken(String username, String role, long expiration) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}