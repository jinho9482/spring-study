package com.example.auth.api;

import com.example.auth.dto.request.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ApiPlaylist {
    private final FeignPlaylist feignPlaylist;
    public final static List<Map<String, Object>> failList = new ArrayList<>();

    @Async //성공 여부와 상관없이 진행시키고 넘어간다.
    // playlist 끄고 호출해보기
    public void updatePlaylist(UpdateRequest req, Long id) {
        try {
            feignPlaylist.updateNickname(req, id);
        } catch (Exception e) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("request", req);
            failList.add(map);
        }
    }

//    @Scheduled(cron = "*/3 * * * *")
//    public void send() {
//        failList.forEach(map -> {
//            updatePlaylist((UpdateRequest) map.get("request"), (Long) map.get("id"));
//        });
//    }

    // 2가지
    // http method : GET, POST, PUT, DELETE
    // CORS (Cross-Origin Resource Sharing)
    // localhost:5173 -> localhost:8080 (front에서 back으로 다음 주소로 delete를 날림, browser는 다른 domain으로 인식)
    // method OPTIONS : 신뢰할 수 있는지 확인




}
