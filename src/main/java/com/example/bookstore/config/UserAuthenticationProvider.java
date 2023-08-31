package com.example.bookstore.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bookstore.model.entity.User;
import com.example.bookstore.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthenticationProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    private final UserService userService;

    public UserAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void init() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    public String createToken(String username) {
        Date now = new Date();
        Date validityDuration = new Date(now.getTime() + 24 * 60 * 1000);

        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        return JWT
                .create()
                .withIssuer(username)
                .withExpiresAt(validityDuration)
                .sign(algorithm);
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.secretKey);

        JWTVerifier jwtVerifier = JWT.require(algorithm).build();

        DecodedJWT decodedJwt = jwtVerifier.verify(token);

        User user = this.userService.findByUsername(decodedJwt.getIssuer());

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }
}
