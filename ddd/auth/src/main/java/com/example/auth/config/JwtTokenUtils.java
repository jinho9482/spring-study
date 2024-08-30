package com.example.auth.config;


import com.example.auth.global.domain.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtils {
    // 토큰 만료 시간 (단위 : milisecond)

    @Value("${token.expiration}") // yaml에서 값을 가져온다.
    private Long tokenExpiration;
    // secret key가 존재한다.
    @Value("${token.secret}") //
    private String tokenSecret; // "vsdab1232fkewbquriebp12315555555aa" 이렇게 실물을 적는 것을 하드코딩이라고 한다.
    // 토큰 만들기
    public String createToken(User user) {
        SecretKey secretKey = Keys.hmacShaKeyFor(tokenSecret.getBytes());
        String token = Jwts.builder()
                .claim("id", user.getId()) // token에 넣을 데이터를 claim해준다.
                .claim("nickname", user.getNickname())
                .signWith(secretKey) // algorithm과 secret key 를 넣어준다.
                .expiration(new Date(new Date().getTime() + tokenExpiration)) // 만료 시간을 넣어준다.
                .compact();
        return token;
    }
    // 토큰 검증
    public TokenInfo parseToken(String token) {
        Claims payload = (Claims) Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .build()
                .parse(token)
                .getPayload();
        System.out.println(payload);
        return TokenInfo.fromClaims(payload);
    }

}
