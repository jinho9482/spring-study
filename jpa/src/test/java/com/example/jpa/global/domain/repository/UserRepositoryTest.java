package com.example.jpa.global.domain.repository;

import com.example.jpa.global.domain.entity.User;
import com.example.jpa.InitData;
import jakarta.transaction.Transactional;
//import static org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
    @Transactional
    class UserRepositoryTest extends InitData {

    @Test
    void findByUsername() {
        // given
        String username = "Jinho7";

        // when
        List<User> ByUsername = userRepository.findByUsername(username);

        // then

        // 길이가 1
        assertEquals(1, ByUsername.size());
        // nickname은 Hola7
        assertEquals("Hola7", ByUsername.get(0).getNickname());
    }

    @Test
    void findByNicknameContainingOrderByIdDesc() {
        String nickname = "Hola7";
        List<User> ByNickname = userRepository.findByNicknameContainingOrderByIdDesc(nickname);
        // 길이는 1
        // password는 1234
        assertEquals(1, ByNickname.size());
        assertEquals("1234", ByNickname.get(0).getPassword());

    }


}