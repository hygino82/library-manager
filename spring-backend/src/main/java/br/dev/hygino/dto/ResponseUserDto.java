package br.dev.hygino.dto;

import java.util.UUID;

public record ResponseUserDto(
    UUID id,
    String name,
    String username,
    String schoolAttribute
) {
    
}
