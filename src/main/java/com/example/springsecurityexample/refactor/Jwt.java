package com.example.springsecurityexample.refactor;

import io.jsonwebtoken.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Jwt {
    final static private String SECRET_KEY = "secret";
    final private String token;
    @Getter final private String username;
    final private Claims claims;
    @Getter final private Collection<GrantedAuthority> authorities;

    private Jwt(String authorizationHeader) {
        token = authorizationHeader.substring(7); // the string after "Bearer " is the jwt
        claims = this.validateAndGetAllClaims();
        username = this.extractUsername();
        authorities = this.extractAuthorities();
    }

    public static Jwt createFrom(String authorizationHeader) {
        return new Jwt(authorizationHeader);
    }

    public static String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", authorities);
        return createToken(claims, username);
    }

    private static String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Claims validateAndGetAllClaims() throws SignatureException {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token) // <- Here is where the jwt validation itself (and date) takes place
                .getBody();
    }

    private String extractUsername() {
        return extractClaim(Claims::getSubject);
    }

    private Collection<GrantedAuthority> extractAuthorities() {
        List<Map> auth = (List<Map>) claims.get("authorities");
        Collection<GrantedAuthority> authoritiesFromJwt = auth.stream()
                .map(e -> e.get("authority").toString())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authoritiesFromJwt;
    }

    private <T> T extractClaim(Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(claims);
    }

}
