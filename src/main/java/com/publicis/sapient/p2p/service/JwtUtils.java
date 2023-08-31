package com.publicis.sapient.p2p.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.publicis.sapient.p2p.exception.BusinessException;
import com.publicis.sapient.p2p.exception.util.ErrorCode;
import com.publicis.sapient.p2p.model.CookieResponse;
import com.publicis.sapient.p2p.model.TokenResponse;
import com.publicis.sapient.p2p.model.User;
import com.publicis.sapient.p2p.repository.Impl.UserRepositoryImpl;
import com.publicis.sapient.p2p.utils.Constants;
import com.publicis.sapient.p2p.utils.EncryptionUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Configuration
public class JwtUtils {

    private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final String SECRET_KEY = "my-secret-key";
    private static final String EMAIL = "email";
    private static final String TOKEN_COOKIE_NAME = "token";
    private static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";
    private static final String NORMAL_COOKIE_NAME = "auth";
    private static final Algorithm JWT_ALGORITHM = Algorithm.HMAC256(SECRET_KEY);
    private static final JWTVerifier JWT_VERIFIER = JWT.require(JWT_ALGORITHM).build();
    private static final int TOKEN_EXPIRATION = 900;
    private static final int REFRESH_TOKEN_EXPIRATION = 28800;
    private static final int ZERO_EXPIRATION = 0;
    private static final int MAX_REFRESH_WINDOW_SECONDS = 30;


    @Autowired
    public UserRepositoryImpl userRepository;

    @Autowired
    public EncryptionUtil encryptionUtil;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    public CookieResponse generateCookie(String email) {
        logger.info("Entering generateCookie method inside JwtUtils");
        User user = userRepository.findUserByEmail(email);
        Instant now = Instant.now();
        String token = createToken(now, user,TOKEN_EXPIRATION);
        String refreshToken = createToken(now, user, REFRESH_TOKEN_EXPIRATION);

        Cookie tokenCookie = createCookie(TOKEN_COOKIE_NAME, token, TOKEN_EXPIRATION, true);
        Cookie refreshTokenCookie = createCookie(REFRESH_TOKEN_COOKIE_NAME, refreshToken, REFRESH_TOKEN_EXPIRATION, true);
        Cookie normalCookie = createCookie(NORMAL_COOKIE_NAME, user.getId() + "_" + REFRESH_TOKEN_EXPIRATION, REFRESH_TOKEN_EXPIRATION, false);


        return new CookieResponse(user.getId(), user.getEmail(), refreshToken, tokenCookie, refreshTokenCookie, normalCookie);
    }

    public CookieResponse removeCookie() {
        logger.info("Entering removeCookie method inside JwtUtils");
        Cookie tokenCookie = createCookie(TOKEN_COOKIE_NAME, null, ZERO_EXPIRATION, true);
        Cookie refreshTokenCookie = createCookie(REFRESH_TOKEN_COOKIE_NAME, null, ZERO_EXPIRATION, true);
        Cookie normalCookie = createCookie(NORMAL_COOKIE_NAME, null, ZERO_EXPIRATION, false);
        return new CookieResponse(null, null, null, tokenCookie, refreshTokenCookie, normalCookie);
    }

    private String createToken(Instant now, User user, int expiration) {
        return JWT.create()
                .withIssuedAt(now)
                .withExpiresAt(now.plusSeconds(expiration))
                .withClaim("userId", user.getId())
                .withClaim(EMAIL, user.getEmail())
                .withClaim("firstName", user.getFirstName())
                .withClaim("lastName", user.getLastName())
                .sign(JWT_ALGORITHM);
    }

    private Cookie createCookie(String name, String value, int expiration, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expiration);
        cookie.setHttpOnly(httpOnly);
        cookie.setSecure(false);
        cookie.setPath("/");
        return cookie;
    }

    public Optional<String> getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return Optional.empty();
        }

        for (Cookie cookie : cookies) {
            if (!cookie.getName().equals(TOKEN_COOKIE_NAME)) {
                continue;
            }
            return Optional.of(cookie.getValue());
        }
        return Optional.empty();
    }

    public TokenResponse validateCookieAndToken(HttpServletRequest request) {
        logger.info("Entering validateCookieAndToken method inside JwtUtils");
        Cookie[] cookies = request.getCookies();
        TokenResponse tokenResponse = new TokenResponse();
        if (cookies == null) {
            logger.warn(Constants.UNAUTHENTICATED_MESSAGE);
            throw new BusinessException(ErrorCode.UNAUTHENTICATED, Constants.UNAUTHENTICATED_MESSAGE);
        }

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(TOKEN_COOKIE_NAME)) {
                tokenResponse.setToken(cookie.getValue());
            }
            if (cookie.getName().equals(REFRESH_TOKEN_COOKIE_NAME)) {
                tokenResponse.setRefreshToken(cookie.getValue());
            }
        }

        return tokenResponse;
    }

    public CookieResponse getTokenFromCookie(HttpServletRequest request) {
        logger.info("Entering getTokenFromCookie method inside JwtUtils");
        TokenResponse tokenResponse = validateCookieAndToken(request);
        if(tokenResponse.getRefreshToken() == null) {
            logger.warn(Constants.UNAUTHENTICATED_MESSAGE);
            throw new BusinessException(ErrorCode.UNAUTHENTICATED, Constants.UNAUTHENTICATED_MESSAGE);
        }
        if(tokenResponse.getToken() == null) {
            String email = getUsernameFromToken(tokenResponse.getRefreshToken());
            return generateCookie(email);
        }
        String email = getUsernameFromToken(tokenResponse.getToken());
        return generateCookie(email);
    }



    public Optional<DecodedJWT> getValidatedToken(String token) {
        try {
            return Optional.of(JWT_VERIFIER.verify(token));
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }

    public boolean validateJwtToken(String token) {
        try {
            return JWT_VERIFIER.verify(token).getExpiresAt().before(new Date(System.currentTimeMillis()));
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public boolean validateJwtTokenAndClaims(String token, String emailid) {
        try {
            return (JWT_VERIFIER.verify(token).getExpiresAt().after(new Date(System.currentTimeMillis())) && JWT_VERIFIER.verify(token).getClaim(EMAIL).asString().equalsIgnoreCase(emailid));
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public boolean validateJwtTokenAndClaimsAndPassword(String token, String emailid, String password) {
        try {
            User user = userRepository.findUserByEmail(emailid);
            return (JWT_VERIFIER.verify(token).getExpiresAt().after(new Date(System.currentTimeMillis())) && JWT_VERIFIER.verify(token).getClaim(EMAIL).asString().equalsIgnoreCase(emailid)) && (user != null && passwordEncoder.matches(encryptionUtil.decrypt(password), user.getPassword()));
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public boolean validateBCryptPassword(String emailid, String password) {
        try {
            User user = userRepository.findUserByEmail(emailid);
            if (!passwordEncoder.matches(encryptionUtil.decrypt(password), user.getPassword())) {
                throw new BusinessException(ErrorCode.UNAUTHENTICATED, Constants.AUTH_INVALID_PASSWORD);
            } else {
                return true;
            }
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            logger.info("Entering getUsernameFromToken method inside JwtUtils");
            return JWT_VERIFIER.verify(token).getClaim(EMAIL).asString();
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, Constants.TOKEN_EXPIRED);
        }
    }

    public String getUserIdFromToken(String token) {
        try {
            logger.info("Entering getUserIdFromToken method inside JwtUtils");
            return JWT_VERIFIER.verify(token).getClaim("userId").asString();
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED, Constants.TOKEN_EXPIRED);
        }
    }

    public boolean isRefreshable(HttpServletRequest request) {
        Optional<String> token = getToken(request);
        if (token.isEmpty()) {
            return false;
        }
        Instant expiryTime = JWT.decode(token.get()).getExpiresAtAsInstant();
        Instant canBeRefreshedAfter = expiryTime.minusSeconds(MAX_REFRESH_WINDOW_SECONDS);
        return Instant.now().isAfter(canBeRefreshedAfter);
    }

}

