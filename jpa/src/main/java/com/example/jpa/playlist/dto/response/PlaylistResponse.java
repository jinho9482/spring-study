package com.example.jpa.playlist.dto.response;


import com.example.jpa.global.domain.dto.UserDto;
import com.example.jpa.global.domain.entity.Playlist;
import com.example.jpa.global.domain.entity.User;

public record PlaylistResponse(
        Long id,
        String title,
        UserDto user
) {
    // Playlist type에서 PlaylistResponse type을 만들기 때문에 from을 쓴다.
    public static PlaylistResponse from(Playlist playlist) {
        User user = playlist.getUser();
        UserDto userDto = new UserDto(user.getId(), user.getNickname());
        return new PlaylistResponse(playlist.getId(), playlist.getTitle(), userDto);
    }

}
