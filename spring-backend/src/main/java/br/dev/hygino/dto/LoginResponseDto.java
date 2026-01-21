package br.dev.hygino.dto;

public record LoginResponseDto(
        String accessToken,
        Long expiresIn) {
}