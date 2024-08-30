package com.example.playlist.service;


import com.example.playlist.config.TokenInfo;
import com.example.playlist.dto.request.UpdateRequest;
import com.example.playlist.global.domain.entity.Playlist;
import com.example.playlist.dto.request.PlaylistRequest;
import com.example.playlist.dto.response.PlaylistResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PlaylistService {
    void save(PlaylistRequest playlist, TokenInfo tokenInfo);
    List<PlaylistResponse> getAll();

    void updateNickname(UpdateRequest req, Long id);

}
