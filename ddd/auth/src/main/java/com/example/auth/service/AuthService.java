package com.example.auth.service;

import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.request.SignUpRequest;
import com.example.auth.dto.request.UpdateRequest;
import com.example.auth.dto.response.UserResponse;
import com.example.auth.global.domain.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

public interface AuthService {
    void save(Map<String, String> map);
    List<UserResponse> getAll();
    String login(LoginRequest req);
    void signUp(SignUpRequest req);

    Object getPlayList();

    void updateMyInfo(UpdateRequest req, Long id);
}
