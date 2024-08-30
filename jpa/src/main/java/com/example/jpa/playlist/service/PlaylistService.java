package com.example.jpa.playlist.service;


import com.example.jpa.global.domain.entity.Playlist;
import com.example.jpa.playlist.dto.request.PlaylistRequest;
import com.example.jpa.playlist.dto.response.PlaylistResponse;

import java.util.List;

public interface PlaylistService {
    void save(PlaylistRequest playlist);
    List<PlaylistResponse> getAll();

}
