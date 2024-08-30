package com.example.auth.service;

import com.example.auth.api.ApiPlaylist;
import com.example.auth.api.FeignPlaylist;
import com.example.auth.config.JwtTokenUtils;
import com.example.auth.config.TokenInfo;
import com.example.auth.dto.request.LoginRequest;
import com.example.auth.dto.request.SignUpRequest;
import com.example.auth.dto.request.UpdateRequest;
import com.example.auth.dto.response.UserResponse;
import com.example.auth.global.domain.entity.User;
import com.example.auth.global.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service // Bean에 올려야 할 service이기 때문에 붙여줌
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtTokenUtils jwtTokenUtils;
    private final PasswordEncoder passwordEncoder;
    private final FeignPlaylist feignPlaylist;
    private final ApiPlaylist apiPlaylist;

    public String login(LoginRequest req) {
        // DB에 있는 user를 찾는다. username을 가지고
        // password를 비교한다.
        // 맞으면 토큰을 return할 것디ㅏ.
        List<User> byName = userRepository.findByUsername(req.username());
        if(byName.isEmpty()) throw new NotFoundException("User Not Found");
        User user = byName.get(0);

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            throw new IllegalArgumentException("You can't login");
        }
        return jwtTokenUtils.createToken(user);
    }

    public void signUp(SignUpRequest req) {
        String encodedPassword = passwordEncoder.encode(req.password());
        if (!userRepository.findByUsername(req.username()).isEmpty())
            throw new IllegalArgumentException("U are already signed up");
        User entity = req.toEntity(encodedPassword);
        userRepository.save(entity);
    }

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

    @Override
    public Object getPlayList() {
//        const data = await axios("get", "/api/v1/playlists")
        return null;
    }

    @Override
    @Transactional
    public void updateMyInfo(UpdateRequest req, Long id) {

        Optional<User> getUserById = userRepository.findById(id);
        User user = getUserById.orElseThrow(() -> new IllegalArgumentException("없는 유저"));

        // 2가지 방법이 존재한다. (save, dirty check)
        // save
        user.setNickname(req.nickname());
        user.setPassword(passwordEncoder.encode(req.password()));
        apiPlaylist.updatePlaylist(req, id);
//        feignPlaylist.updatePlaylistUser(updateRequest, id); 이렇게 하면 이게 성공할 때까지 transaction commit 안됨

//        userRepository.save(user);

        // dirty checking (transaction이 끝날 때 자동으로 checking을 한다. -> @Transactional 필요)



    }
}
