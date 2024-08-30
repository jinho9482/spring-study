package com.example.auth.dto.request;

// put으로 연동
// endpoint ; /api/v1/auth
// body : UpdateRequest
// update
public record UpdateRequest(
        String password,
        String nickname
) {
    
}
