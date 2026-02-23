package com.josephhieu.springsecurity.service;

import com.josephhieu.springsecurity.dto.request.AuthenticationRequest;
import com.josephhieu.springsecurity.dto.response.AuthenticationResponse;
import com.josephhieu.springsecurity.dto.response.IntrospectResponse;
import com.josephhieu.springsecurity.exception.AppException;
import com.josephhieu.springsecurity.exception.ErrorCode;
import com.josephhieu.springsecurity.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;

    @Value("${jwt.secret}")
    private String SECRET;

    private SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        var user = userRepository.findByUsername(request.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String token = generateToken(user.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    public IntrospectResponse introspect(String token) {
        try {
            Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token);

            return IntrospectResponse.builder()
                    .valid(true)
                    .build();

        } catch (Exception e) {
            return IntrospectResponse.builder()
                    .valid(false)
                    .build();
        }
    }

    private String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuer("hieudepzai")
                .issuedAt(new Date())
                .expiration(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", "USER")
                .signWith(SECRET_KEY)
                .compact();
    }
}
