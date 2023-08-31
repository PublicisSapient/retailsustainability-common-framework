package com.publicis.sapient.p2p.model;

import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CookieResponse {
    private String userId;
    private String email;
    private String refreshToken;
    private Cookie tokenCookie;
    private Cookie refreshTokenCookie;
    private Cookie normalCookie;
}
