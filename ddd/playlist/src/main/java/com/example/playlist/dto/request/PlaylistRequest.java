package com.example.playlist.dto.request;

import com.example.playlist.config.TokenInfo;
import com.example.playlist.global.domain.entity.Playlist;

public record PlaylistRequest(
        String title,
        Long userId
) {
    public Playlist toEntity(TokenInfo tokenInfo) {
        return Playlist.builder().title(title).userId(tokenInfo.id()).userNickname(tokenInfo.nickname()).build();
    }

}
