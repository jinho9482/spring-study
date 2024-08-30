package com.example.song.service;

import com.example.song.global.domain.entity.Song;
import com.example.song.dto.request.SongRequest;

import java.util.List;
import java.util.Map;

public interface SongService {
    List<Song> getAll();
//    void save(Map<String, String> map);
    void save(SongRequest req);

    Song getById(Long id);
    Song update(SongRequest req, Long id);
}

