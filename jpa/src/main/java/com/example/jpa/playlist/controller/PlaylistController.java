package com.example.jpa.playlist.controller;

import com.example.jpa.global.domain.entity.Playlist;
import com.example.jpa.playlist.dto.request.PlaylistRequest;
import com.example.jpa.playlist.dto.response.PlaylistResponse;
import com.example.jpa.playlist.service.PlaylistService;
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

    @PostMapping
    public void createPlaylist(@RequestBody PlaylistRequest req) {
        playlistService.save(req);
    }

    @GetMapping
    public List<PlaylistResponse> getAllPlaylists() {
        return playlistService.getAll();
    }

}
