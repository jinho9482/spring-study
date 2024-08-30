package com.example.jpa.song.service;

import com.example.jpa.global.domain.entity.Song;
import com.example.jpa.global.domain.repository.SongRepository;
import com.example.jpa.song.dto.request.SongRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;

    @Override
    public List<Song> getAll() {
        return songRepository.findAll();
    }

    @Override
    public void save(SongRequest req) {
//      javascript에서는 const {title, lyrics} = map;으로 끝낼 수 있다.
//        String title = map.get("title");
//        String lyrics = map.get("lyrics");
//        Song songs = new Song(null, title, lyrics, LocalDateTime.now());
        // builder는 field값의 순서를 외우지 않아서 좋지만, 코드가 길어지는 것이 단점이다.
//        Song song = Song.builder().title(title).lyrics(lyrics).createdAt(LocalDateTime.now()).build();
        songRepository.save(req.toEntity());
    }

    @Override
    @Transactional
    public Song getById(Long id) {
        Optional<Song> byId = songRepository.findById(id); // Optional : null check 목적, 해당 id가 table안에 없을 수도 있기 때문에 optional을 붙여준다.
        Song song = byId.orElse(new Song());
        song.setTitle("******");
        return song; // byId가 empty이면 new Song을, 있으면 byId를 return

//        if (byId.isEmpty()) return null; // isEmpty() : null인가?
//        return byId.get(); // get()이 왜 필요하지?
    }

    @Override
    @Transactional
    public Song update(SongRequest req, Long id) {
        // 없으면 던지기 때문에 optional이 아니다.
//        Song song = songRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        Song song = songRepository.findById(id).orElseThrow(IllegalArgumentException::new);
//        if (songById.isEmpty()) throw new IllegalArgumentException();
//        Song song = songById.get();
        // dirty check
        song.setTitle(req.title());
        song.setLyrics(req.lyrics());
        return song;

    }
}
