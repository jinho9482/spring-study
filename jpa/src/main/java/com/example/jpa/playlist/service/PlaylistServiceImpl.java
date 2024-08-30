package com.example.jpa.playlist.service;

import com.example.jpa.global.domain.dto.PlaylistDto;
import com.example.jpa.global.domain.dto.UserDto;
import com.example.jpa.global.domain.entity.Playlist;
import com.example.jpa.global.domain.entity.User;
import com.example.jpa.global.domain.repository.PlaylistRepository;
import com.example.jpa.playlist.dto.request.PlaylistRequest;
import com.example.jpa.playlist.dto.response.PlaylistResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    @Override
    public void save(PlaylistRequest req) {
        playlistRepository.save(req.toEntity());
    }

    // 성능은 for이 map보다 빠르다.
    @Override
    public List<PlaylistResponse> getAll() {
        return playlistRepository.findAll()
                .stream()
                .map(playlist -> {
                    return PlaylistResponse.from(playlist); // .map(PlaylistResponse::from)와 같다. playlist가 겹치게 들어가므로

                }).toList();
    }


}
