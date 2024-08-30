package com.example.auth.controller;

import com.example.auth.api.FeignPlaylist;
import com.example.auth.config.JwtTokenUtils;
import com.example.auth.config.TokenInfo;
import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.request.SignUpRequest;
import com.example.auth.dto.request.UpdateRequest;
import com.example.auth.dto.response.UserResponse;
import com.example.auth.global.domain.entity.Playlist;
import com.example.auth.global.domain.repository.UserRepository;
import com.example.auth.service.AuthService;
import com.example.auth.global.domain.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController // data를 return 하는 controller, IOC container에 등록이 된다.
@RequestMapping("/api/v1/auths")
@RequiredArgsConstructor
public class AuthController {
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthService authService; // bean에 등록이 된 게 없어서 가져올 게 없다.
    private final FeignPlaylist feignPlaylist;
    private final UserRepository userRepository;
//   header를 통해 token을 넣어준다. token은 authorization이라는 field에 값을 넣어준다. 보통 Bearer + alpha 이렇게 보내준다.

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return authService.login(req);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody SignUpRequest  req) {
        authService.signUp(req);
    }

    @GetMapping("/me")
    public TokenInfo getMe(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        return jwtTokenUtils.parseToken(token);
    }

    @GetMapping
    public List<UserResponse> getAll(@RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        jwtTokenUtils.parseToken(token);
        return authService.getAll();
    }

    @GetMapping("/play")
    public Object getPlaylist() {
        return feignPlaylist.getAllPlaylists();
    }


    @PostMapping
    public void save() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "Jinho");
        map.put("nickname", "Hola");
        map.put("password", "1234");
        authService.save(map);
    }

    @GetMapping("test")
    public String test() {
        return "조진호";
    }

    @PutMapping
    public void updateMyInfo(@RequestBody UpdateRequest req, @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        authService.updateMyInfo(req, tokenInfo.id());
    }

}

