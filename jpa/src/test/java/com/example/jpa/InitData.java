package com.example.jpa;

import com.example.jpa.global.domain.entity.Playlist;
import com.example.jpa.global.domain.entity.Song;
import com.example.jpa.global.domain.entity.User;
import com.example.jpa.global.domain.repository.PlaylistRepository;
import com.example.jpa.global.domain.repository.SongRepository;
import com.example.jpa.global.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest // Bean 에 있는 걸 쓰기 위해
@Transactional
public class InitData {
    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected PlaylistRepository playlistRepository;

    @Autowired
    protected SongRepository songRepository;


    protected Song song;

    @BeforeEach
    void init() {
        Song song = Song.builder().title("Hello").lyrics("Hello, it's me").createdAt(LocalDateTime.now()).build();
        songRepository.save(song);
        for(int i = 0; i<10; i++) {
            User user = new User(null,
                    "Jinho" + i,
                    "1234",
                    "Hola" + i,
                    null);
            userRepository.save(user);
            Playlist playlist = new Playlist(
                    null, "title" + i, user
            );
            playlistRepository.save(playlist);
        }
    }
}
