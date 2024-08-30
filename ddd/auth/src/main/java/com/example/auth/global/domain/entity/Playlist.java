package com.example.auth.global.domain.entity;

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

    @JoinColumn(name = "USER_ID")
    @ManyToOne(fetch = FetchType.LAZY) // User 입장에서는 playlist를 여러 개 가질 수 있고 반대는 1개만 된다.
    private User user;
}
