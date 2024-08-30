package com.example.jpa.song.controller;

import com.example.jpa.InitData;
import com.example.jpa.song.dto.request.SongRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SongControllerTest extends InitData {

    @Autowired
    private MockMvc mockMvc;
    String baseUrl = "/api/v1/songs";

//    @Test
//    void getAll() {
//    }
//
    @Test
    void save() throws Exception {

        // given
        ObjectMapper mapper = new ObjectMapper(); // body를 작성해서 request 하면 알아서 mapping 해준다.
        SongRequest songRequest = new SongRequest("title1", "가사");
        String json = mapper.writeValueAsString(songRequest);

        // when & then
        // controller test는 들어갔냐 안 들어갔냐만 따짐 (o,x 를 따짐), 값을 따지지 않음
        mockMvc.perform(post(baseUrl).content(json))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

//    /api/v1/songs에서 get을 성공했을 때 200 status code가 나오고 하나의 object가 return 됨
    @Test
    void getById() throws Exception {
        // given (variable)
        long id = song.getId();

        ResultActions perform = mockMvc.perform(get(baseUrl + song.getId()));

        // when & then (요청을 보내놓고 바로 테스트하기 때문에 같이 있다.)

                perform.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(song.getId()))                  // perform() 은 data를 return 받음, view가 아니라, like @RestController
                .andExpect(jsonPath("$.title").value(song.getTitle()))                  // perform() 은 data를 return 받음, view가 아니라, like @RestController
                .andExpect(jsonPath("$.lyrics").value(song.getLyrics()))                  // perform() 은 data를 return 받음, view가 아니라, like @RestController
                .andExpect(jsonPath("$.createdAt").isEmpty());                   // perform() 은 data를 return 받음, view가 아니라, like @RestController
//        mockMvc.perform(post("/api/v1/songs").content("Hola"));                   // perform() 은 data를 return 받음, view가 아니라, like @RestController

    }
}