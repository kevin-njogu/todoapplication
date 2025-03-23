package com.kevin.todo.todo_application.usermanagement.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final  String SECRET_KEY="e0b52ff24c699fafec3dc15665dad176f4308c4717bdcf3e030c5b39784ce669";
    private static final long EXPIRATION_TIME = 3600000;

    //1.Method to Generate JWT token. Takes in a map of any custom or extra claims we want to add and a UserDetails object from spring security
    public String generateJwtToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSecretKey())
                .compact();
    }

    //2.Method to Generate JWT token.. Utilized when we do not have any extra claims we want to add hence takes UserDetails object only
    public String generateJwtToken( UserDetails userDetails) {
        return generateJwtToken(new HashMap<>(), userDetails);
    }

    //3. Validate whether the token received belong to the user sharing it and the token is not expired
    public boolean isTokenValid(String jwtToken, UserDetails userDetails) {
        final String username = extractUsername(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    //4. Check if the received token is expired. If expiration date is before the time the token is shared then the token is expired
    private boolean isTokenExpired(String jwtToken) {
        return extractExpirationDate(jwtToken).before(new Date());
    }

    //5. Extract token expiration Date
    private Date extractExpirationDate(String jwtToken) {
        return extractClaim(jwtToken, Claims::getExpiration);
    }

    //6. Extract username /email from the jwt token
    public  String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    //7. Extract all claims our JWT Token
    private Claims extractAllClaims(String jwtToken) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    //8. Extract single claim from our JWT Token.
    public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
        final  Claims claims = extractAllClaims(jwtToken);
        return claimsResolver.apply(claims);
    }

    //9. Generate a secret key for signing our JWTs
    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
