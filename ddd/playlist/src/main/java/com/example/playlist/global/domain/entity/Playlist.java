package com.example.playlist.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "PLAYLISTS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYLIST_ID")
    private Long id;

    @Column(name = "PLAYLIST_TYPE", nullable = false)
    private String title;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "USER_NICKNAME") @Setter
    private String userNickname;

}
