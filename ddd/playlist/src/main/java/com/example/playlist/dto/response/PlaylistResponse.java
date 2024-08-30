package com.example.playlist.dto.response;


import com.example.playlist.global.domain.dto.UserDto;
import com.example.playlist.global.domain.entity.Playlist;

public record PlaylistResponse(
        Long id,
        String title,
        UserDto user
) {
    // Playlist type에서 PlaylistResponse type을 만들기 때문에 from을 쓴다.
    public static PlaylistResponse from(Playlist playlist) {

        UserDto userDto = new UserDto(playlist.getUserId(), playlist.getUserNickname());
        return new PlaylistResponse(playlist.getId(), playlist.getTitle(), userDto);
    }

}
