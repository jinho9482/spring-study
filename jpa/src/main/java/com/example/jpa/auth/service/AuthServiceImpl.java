package com.example.jpa.auth.service;

import com.example.jpa.auth.dto.response.UserResponse;
import com.example.jpa.global.domain.dto.PlaylistDto;
import com.example.jpa.global.domain.entity.User;
import com.example.jpa.global.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service // Bean에 올려야 할 service이기 때문에 붙여줌
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    @Override
    public void save(Map<String, String> map) {
        User user = new User(null, map.get("username"), map.get("password"), map.get("nickname"), null);
        userRepository.save(user); // save()는 JpaRepository에 있기 때문에 따로 선언을 해줄 필요가 없다.
    }

    @Override
    public List<UserResponse> getAll() {

        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.from(user))
                .toList();
    }
}
