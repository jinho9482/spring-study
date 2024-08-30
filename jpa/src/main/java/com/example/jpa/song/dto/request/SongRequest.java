package com.example.jpa.song.dto.request;

import com.example.jpa.global.domain.entity.Song;

import java.time.LocalDateTime;

// Song type이 되기 위한 존재다.
public record SongRequest (
        String title,
        String lyrics
) {
    public Song toEntity() {
        // builder는 각각의 항목 값을 받아서 build()를 통해 앞의 내용을 하나의 객체로 만든다.
        return Song.builder().title(title).lyrics(lyrics).createdAt(LocalDateTime.now()).build();
    }
}
