package com.example.playlist.dto.request;

// put으로 연동
// endpoint ; /api/v1/playlists/user/:id
// body : UpdateRequest
// update
public record UpdateRequest(
        String nickname
) {
    
}
