package com.example.playlist.playlist.controller;

import com.example.playlist.config.JwtTokenUtils;
import com.example.playlist.config.TokenInfo;
import com.example.playlist.dto.request.UpdateRequest;
import com.example.playlist.global.domain.entity.Playlist;
import com.example.playlist.dto.request.PlaylistRequest;
import com.example.playlist.dto.response.PlaylistResponse;
import com.example.playlist.service.PlaylistService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/api/v1/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;
    private final JwtTokenUtils jwtTokenUtils;


    @PostMapping
    public void createPlaylist(@RequestBody PlaylistRequest req, @RequestHeader("Authorization") String bearerToken) {
        String token = bearerToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseToken(token);
        playlistService.save(req, tokenInfo);
    }

    @GetMapping
    public List<PlaylistResponse> getAllPlaylists() {
        return playlistService.getAll();
    }

    @PutMapping("/user/{uid}")
    public void updateNickname(@RequestBody UpdateRequest req, @PathVariable("uid") Long id) {
        playlistService.updateNickname(req, id);
    }

}
