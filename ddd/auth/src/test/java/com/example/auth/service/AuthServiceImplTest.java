package com.example.auth.service;

import com.example.auth.dto.request.SignUpRequest;
import com.example.auth.global.domain.entity.User;
import com.example.auth.global.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AuthServiceImplTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthServiceImpl authService;

    @Test
    void login() {
        User user = new User(null, "Jinho", "1234", "Hola", null);
        userRepository.save(user);
    }

    @Test
    void signUp() {
        SignUpRequest req = new SignUpRequest("Jinho", "1234", "Hola");
        authService.signUp(req);

        List<User> byUsername = userRepository.findByUsername("Jinho");
        assertEquals(1,byUsername.size());
        assertNotEquals(
                req.password(),
                byUsername.get(0).getPassword()
        );
        System.out.println(req.password()+"  "+
                byUsername.get(0).getPassword());

    }

    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    void matchPassword() {
        assertTrue(passwordEncoder.matches("1234", "$2a$10$xtJ5pHs/mLI4bk4DVNDv8.KNOcW1bz.McdY8TzffznW69FsH5APYa"));
    }
}