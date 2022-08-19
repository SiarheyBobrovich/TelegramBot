package by.it_academy.user.utils;

import by.it_academy.user.exceptions.PropertiesNotFoundException;
import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static io.jsonwebtoken.lang.Classes.getResourceAsStream;

public class JwtTokenUtil {

    private static final String JWT_SECRET;

    private static final String JWT_ISSUER;

    static {
        Properties properties = new Properties();
        try {
            properties.load(getResourceAsStream("jwt.properties"));

        } catch (IOException e) {
            throw new PropertiesNotFoundException("Проверьте jwt.properties файл");
        }

        JWT_SECRET = properties.getProperty("jwt.secret");
        JWT_ISSUER = properties.getProperty("jwt.issuer");
    }

    private JwtTokenUtil() {
    }

    public static String generateAccessToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(JWT_ISSUER)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public static String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public static boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}