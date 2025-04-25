package com.GymManager.Backend.web.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils implements iJwtService {
    private static final String SECRET_KEY =  "skdjhsjgdsdbadevwv62342iuuaapo";
    private static final long EXPIRATION_MS = 86400000;

    @Override
    public String createJwt(@Valid String username, @Valid String role){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create()
                .withSubject(username)
                .withClaim("authority", role)
                .withIssuer("backend-gymmanager")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .sign(algorithm);
    }
    @Override
    public boolean isValidJwt(@Valid String jwt){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        try{
             JWT.require(algorithm)
                    .build()
                    .verify(jwt);
             return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public String getUser( @Valid String jwt){
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.require(algorithm)
                .build()
                .verify(jwt)
                .getSubject();
    }
}
