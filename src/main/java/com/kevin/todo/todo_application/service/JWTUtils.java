package com.kevin.todo.todo_application.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTUtils {

    private final SecretKey Key;
    private static final  long EXPIRATION_TIME =86400000; //24 hours

    public JWTUtils() {
        String secretString ="80c93895a74e0e76858b23c7e8c1d09c8b96c171ca45cd860db47619e310b5d23b8a7c8068eba5c1aa87da291a9cefc5d8a22a07e3563e9a7dd2bccc928b4caf2fddabedf4f4b6ddd3cda0815d1a5555348cce779f5bb779a4878bd529577f25b646a4d99f1c903e0958bca43c16f0474650aa24f866736efdd5fd11489226a34f9094df727a414553c9dbe4707b781b723ed3f7b16214ca32ddb8f84579937f380e7eefa7855b753c0ec65f63130bfe933bf11ee08eb91535f53b292253b7005a9bbf4659b58783a1e1dd73b6c01ba1076efd364d3ee9f10217c1804e5e33c3c3d7baa24669b3d8048d1d129b530af658a522e36fab95b316f66547081e1e08";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.Key= new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }

    public String generateRefreshToken(HashMap<String, Object>claims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Key)
                .compact();
    }

    public String extractUserName(String token) {
        return  extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T>claimsTFunction) {
        return claimsTFunction.apply(
                Jwts.parser()
                        .verifyWith(Key)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
        );
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }
}
