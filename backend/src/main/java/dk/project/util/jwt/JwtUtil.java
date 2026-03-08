package dk.project.util.jwt;

import dk.project.config.DotEnv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

public abstract class JwtUtil {

    // Shared attributes for all JWT types
    protected static final String SECRET = DotEnv.get("JWT_SECRET");
    protected static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    protected static final long ACCESS_EXPIRATION = Long.parseLong(DotEnv.get("ACCESS_EXPIRATION"));
    protected static final long REFRESH_EXPIRATION = Long.parseLong(DotEnv.get("REFRESH_EXPIRATION"));

    // _____________________________________________________________________

    public static boolean isValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(KEY)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

}