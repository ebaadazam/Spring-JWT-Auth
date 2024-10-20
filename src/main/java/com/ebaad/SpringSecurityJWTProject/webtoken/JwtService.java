package com.ebaad.SpringSecurityJWTProject.webtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

// We created this service file for managing the jwt operations
@Service
public class JwtService {

    private static final String SECRET = "63A2E3867291A701DAA9A372C0D25747F6CFC20D1230E42DA68FED79B38B29CE03157C1948D91F717C545E50A0781440169AF8311B0F82B87BE153A855E1F45B";
    private static final long VALIDITY = TimeUnit.MINUTES.toMillis(30);

    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("iss", "ebaad_azam");
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plusMillis(VALIDITY)))
                .signWith(generatekey())
                .compact(); // convert string to json format
    }

    private SecretKey generatekey() {
        // This will decode the secret key and convert it into secret key obj
        byte[] decodedKey = Base64.getDecoder().decode(SECRET);

        // Convert the above byte array into secret key
        return Keys.hmacShaKeyFor(decodedKey);
    }

    // for JWTAuthenticationFilter
    public String extractUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generatekey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean isTokenValid(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
