package com.example.playlist.config;



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


    // 토큰을 까서 저장
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
