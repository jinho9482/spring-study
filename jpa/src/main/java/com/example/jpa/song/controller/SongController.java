package com.example.jpa.song.controller;


import com.example.jpa.global.domain.entity.Song;
import com.example.jpa.song.dto.request.SongRequest;
import com.example.jpa.song.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Controller는 endpoint (user의 input) 값만 구현해준다.
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService; // interface를 불러옴으로써 service와 직접적인 연결이 되지 않는다.

    @GetMapping

    public List<Song> getAll() {
        return songService.getAll();
    }

    @PostMapping
    public void save(@RequestBody SongRequest req) {
//        Map<String, String> map = new HashMap<>();
//        map.put("title", "Hello");
//        map.put("lyrics", "Hello, it's me.");
        songService.save(req);
    }

    @GetMapping("/{id}")
    public Song getById(@PathVariable("id") Long id) {
        return songService.getById(id);
    }
}
