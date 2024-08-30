package com.example.auth.api;

import com.example.auth.dto.request.UpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("PLAYLIST-SERVICE") // PLAYLIST-SERVICE 서버를 eureka에서 찾아 들어간다.
                                 // (괄호 안에는 Eureka에 올라간 서버 이름을 넣어준다.)
public interface FeignPlaylist {
    @GetMapping("/api/v1/playlists")
    Object getAllPlaylists();

    @PutMapping("/user/{uid}")
    void updateNickname(@RequestBody UpdateRequest req, @PathVariable("uid") Long uid);

}
