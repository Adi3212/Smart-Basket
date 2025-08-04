package com.cdac.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.cdac.entites.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

    @Value("${jwt.secret.key}")
    private String jwtSecret;

    @Value("${jwt.expiration.time}")
    private int jwtExpirationMs;

    private SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        log.info("JWT Secret Key initialized. Expiration time: {} ms", jwtExpirationMs);
    }

    // Generate token with username + authorities
    public String generateJwtToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .claim("authorities", extractAuthorityStrings(userPrincipal.getAuthorities()))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    // Parse and validate token, return Claims (payload)
    public Claims validateJwtToken(String jwtToken) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    // Extract username (email) from token payload
    public String getUserNameFromJwtToken(Claims claims) {
        return claims.getSubject();
    }

    // Convert list of GrantedAuthority to list of Strings
    private List<String> extractAuthorityStrings(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    // Convert Strings back to GrantedAuthority list
    public List<GrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
        List<String> roles = claims.get("authorities", List.class);
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // Generate Authentication object from JWT
    public Authentication populateAuthenticationTokenFromJWT(String jwt) {
        Claims claims = validateJwtToken(jwt);
        String email = getUserNameFromJwtToken(claims);
        List<GrantedAuthority> authorities = getAuthoritiesFromClaims(claims);
        return new UsernamePasswordAuthenticationToken(email, null, authorities);
    }
}
