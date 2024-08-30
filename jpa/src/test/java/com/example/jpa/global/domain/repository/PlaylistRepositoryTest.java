package com.example.jpa.global.domain.repository;

import com.example.jpa.InitData;
import com.example.jpa.auth.dto.response.UserResponse;
import com.example.jpa.global.domain.dto.PlaylistDto;
import com.example.jpa.global.domain.entity.Playlist;
import com.example.jpa.global.domain.entity.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // Bean에 들어있는 것을 test하기 위해 붙여줌
@Transactional
class PlaylistRepositoryTest extends InitData {


    @Test
    void findByTitle() {
//        given
        String title = "title3";

//        when
        List<Playlist> byTitle = playlistRepository.findByTitle(title);

//        then
//      list의 길이는 하나다.
        Assertions.assertEquals(1, byTitle.size());
//      위의 assertion이 false이면 아래는 동작하지 않는다.
//      관계된 유저의 username은 Jinho3이다.
        Assertions.assertEquals("Jinho3", byTitle.get(0).getUser().getUsername());
    }
}