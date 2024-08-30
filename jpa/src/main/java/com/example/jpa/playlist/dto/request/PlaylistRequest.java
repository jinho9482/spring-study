package com.example.jpa.playlist.dto.request;

import com.example.jpa.global.domain.entity.Playlist;
import com.example.jpa.global.domain.entity.User;

public record PlaylistRequest(
        String title,
        Long userId
) {
    public Playlist toEntity() {
        return Playlist.builder().title(title).user(new User(userId, null, null, null, null)).build();
    }

}
