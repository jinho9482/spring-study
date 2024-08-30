package com.example.playlist.global.domain.dto;


// 생성자이기 때문에 괄호 안의 구분은 쉼표이다.
public record UserDto(
        Long id,
        String nickname
) {
}
