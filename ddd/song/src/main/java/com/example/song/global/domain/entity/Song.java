package com.example.song.global.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "SONGS") // DB에 저장할 이름
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SONG_ID")
    private Long id;
    @Column(name = "SONG_TITLE")
    private String title;
    @Column(name = "SONG_LYRICS")
    private String lyrics;
    @Column(name = "SONG_CREATED_AT")
    private LocalDateTime createdAt;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
