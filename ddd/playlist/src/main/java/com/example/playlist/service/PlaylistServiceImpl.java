package com.example.playlist.service;

import com.example.playlist.config.TokenInfo;
import com.example.playlist.dto.request.UpdateRequest;
import com.example.playlist.global.domain.entity.Playlist;
import com.example.playlist.global.domain.repository.PlaylistRepository;
import com.example.playlist.dto.request.PlaylistRequest;
import com.example.playlist.dto.response.PlaylistResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    @Override
    public void save(PlaylistRequest req, TokenInfo tokenInfo) {
        playlistRepository.save(req.toEntity(tokenInfo));
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

    @Override
    @Transactional
    public void updateNickname(UpdateRequest req, Long id) {

        playlistRepository.updateUserNickname(req.nickname(), id);
//        Optional<Playlist> byId = playlistRepository.findById(id);
//        Playlist playlist = byId.orElseThrow(() -> new IllegalArgumentException("플레이스트가 존재하지 않습니다."));
//        playlist.setUserNickname(req.nickname());
    }


}
