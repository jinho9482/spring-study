package com.example.auth.config;

import com.example.auth.global.domain.entity.User;
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SignatureException;

@SpringBootTest
class JwtTokenUtilsTest {
    @Autowired JwtTokenUtils jwtTokenUtils;

    @Test
    void createToken() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        String token = jwtTokenUtils.createToken(
                new User(1L, "Jinho", "1234", "Hola", null)
        );
//        Assertions.assertEquals(3, token.split(".").length);
        System.out.println(token);
        Assertions.assertNotNull(token);
    }

    @Test
    void parseToken() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        String token = jwtTokenUtils.createToken(new User(1L, "Jinho", "1234", "Hola", null));
//        jwtTokenUtils.validateToken(token);
        Assertions.assertNotNull(jwtTokenUtils.parseToken(token));
    }

    @Test
    void parseToken2() {
        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwibmlja25hbWUiOiJIb2xhIiwiZXhwIjoxNzEzNTA0ODgxfQ.8oV13gl0K9XbN3KFZ5p7xlCKbxSuIpXtKjn0-Ln0Mts";
        Assertions.assertThrows(SignatureException.class, () -> jwtTokenUtils.parseToken(token));
    }
}