package com.example.jpa.auth.controller;

import com.example.jpa.auth.dto.response.UserResponse;
import com.example.jpa.auth.service.AuthService;
import com.example.jpa.global.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // data를 return 하는 controller, IOC container에 등록이 된다.
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService; // bean에 등록이 된 게 없어서 가져올 게 없다.
    @GetMapping
    public List<UserResponse> getAll() {
        return authService.getAll();
    }

    @PostMapping
    public void save() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "Jinho");
        map.put("nickname", "Hola");
        map.put("password", "1234");
        authService.save(map);
    }
}

