package io.yadnyesh.springbootblog.security;

import io.jsonwebtoken.*;
import io.yadnyesh.springbootblog.exception.BlogApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generateJwtToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

        return token;
    }

        public String getUsernameFromJWT(String token) {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        }

        public boolean validateJwtToken(String token) {
            try {
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                return true;
            } catch (SignatureException e) {
                throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
            } catch (MalformedJwtException e) {
                throw new BlogApiException(HttpStatus.BAD_REQUEST, "Malformed JWT token");
            } catch (ExpiredJwtException e) {
                throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
            } catch (UnsupportedJwtException e) {
                throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
            } catch (IllegalArgumentException e) {
                throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT Claims string is empty");
            }
        }

}
