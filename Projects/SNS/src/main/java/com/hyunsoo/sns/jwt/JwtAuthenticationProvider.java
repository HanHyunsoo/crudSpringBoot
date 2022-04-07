package com.hyunsoo.sns.jwt;

import com.hyunsoo.sns.model.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider {

    @Value("${spring.jwt.secret}")
    private String secretKey;

    // 1시간
    private long tokenValidTime = 1000L * 60 * 60;

    private final UserService userService;

    // 토큰을 만드는 메소드
    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토근에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails user = userService.loadUserByUsername(getUserName(token));

        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    // 토큰에서 username 추출
    public String getUserName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // request header에서 token값을 가져오는 메소드
    public String resolveToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null) return null;

        return getOnlyToken(token);
    }

    // 토큰 유효성, 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // 토큰에서 앞에 Bearer를 분리하는 메소드
    public String getOnlyToken(String token) {
        return token.substring("Bearer ".length());
    }
}
