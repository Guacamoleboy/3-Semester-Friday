package dk.project.security.jwt;

import dk.project.entity.User;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.UUID;

public class JwtService extends JwtUtil {

    // Attributes
    protected static final String CLAIM_USERNAME = "username";
    protected static final String CLAIM_ROLE = "role";
    protected static final String CLAIM_TYPE = "type";

    // _________________________________________________________________________________________________________________

    public static String generateAccessToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim(CLAIM_USERNAME, user.getUsername())
                .claim(CLAIM_ROLE, user.getRole().getName())
                .claim(CLAIM_TYPE, "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    // _________________________________________________________________________________________________________________

    public static String generateRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim(CLAIM_TYPE, "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION))
                .signWith(KEY)
                .compact();
    }

    // _________________________________________________________________________________________________________________

    public static UUID getUserId(String token) {
        return UUID.fromString(getClaims(token).getSubject());
    }

    // _________________________________________________________________________________________________________________

    public static String getRole(String token) {
        return getClaims(token).get(CLAIM_ROLE, String.class);
    }

    // _________________________________________________________________________________________________________________

    public static String getUsername(String token) {
        return getClaims(token).get(CLAIM_USERNAME, String.class);
    }

    // _________________________________________________________________________________________________________________

    public static String getTokenType(String token) {
        return getClaims(token).get(CLAIM_TYPE, String.class);
    }

}