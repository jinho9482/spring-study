package com.example.jpa.song.service;

import com.example.jpa.global.domain.entity.Song;
import com.example.jpa.global.domain.repository.SongRepository;
import com.example.jpa.song.dto.request.SongRequest;
import jakarta.transaction.Transactional;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Bean을 사용하기 위함, 이 annotation이 있으면 test가 끝나고 rollback을 한다.
@Transactional // 이 class가 시작하고 끝날 때 commit 한다.
class SongServiceTest {
    @Autowired // 생성자를 못 만들기 때문에 이걸 사용한다.
    private SongService songService;
    @Autowired
    private SongRepository songRepository;
    Song song;

    @Nested
    // update, updateFail 을 한 test로 묶고 싶다.
    class update {
        //        init();
        // given

        @Test
        void success() {
            SongRequest req = new SongRequest("test1", "test1");
            Long id = song.getId();

            // when
            songService.update(req, id);

            // then
            Song song1 = songRepository.findById(song.getId()).get();
            Assertions.assertEquals("test1", song1.getTitle());
            Assertions.assertEquals("test1", song1.getLyrics());
        }
        @Test
        void IdNotFoundFail() {
            // given
            SongRequest req = new SongRequest("test1", "test1");
            Long id = 10000L;

            // when
            // () -> songService.update(req, id) 함수를 실행했을 때 error가 뜨면 IllegalArgumentException.class 가 throw
            Assertions.assertThrows(IllegalArgumentException.class, () -> songService.update(req, id));

            // then
            Song song1 = songRepository.findById(song.getId()).get();
            Assertions.assertEquals("test", song1.getTitle());
            Assertions.assertEquals("test", song1.getLyrics());
        }
    }

//    @Test // 이게 붙어야 test 가능
////    TDD (Test Driven Development : test를 먼저 setting해놓고 거기에 맞게 개발)
//    void update() {
//
////        init();
//        // given
//
//            SongRequest req = new SongRequest("test1", "test1");
//        Long id = song.getId();
//
//        // when
//        songService.update(req, id);
//
//        // then
//        Song song1 = songRepository.findById(song.getId()).get();
//        Assertions.assertEquals("test1", song1.getTitle());
//        Assertions.assertEquals("test1", song1.getLyrics());
//
//
//    }
//
//    @Test
//    void updateFail() {
//
//        // given
//        SongRequest req = new SongRequest("test1", "test1");
//        Long id = 10000L;
//
//        // when
//        // () -> songService.update(req, id) 함수를 실행했을 때 error가 뜨면 IllegalArgumentException.class 가 throw
//        Assertions.assertThrows(IllegalArgumentException.class, () -> songService.update(req, id));
//
//        // then
//        Song song1 = songRepository.findById(song.getId()).get();
//        Assertions.assertEquals("test", song1.getTitle());
//        Assertions.assertEquals("test", song1.getLyrics());
//
//    }

    @Test // 이게 붙어야 test 가능

    void update2() {
        // given

//        init();

        // when
        Song byId = songRepository.findById(song.getId()).get();

        // then
        Assertions.assertEquals(song, byId);
        Assertions.assertEquals(song.getId(), byId.getId());
        Assertions.assertEquals("test", byId.getTitle());

    }


    @BeforeEach // 각각의 test 가 흘러가기 전에 이것을 먼저 실행한다.

    void init() {
        song = new Song(null, "test", "test", LocalDateTime.now());
        songRepository.save(song);
    }

    @AfterEach // Test가 끝나고 실행하는 것
    void destroy() {
        songRepository.deleteAll();
    }
}