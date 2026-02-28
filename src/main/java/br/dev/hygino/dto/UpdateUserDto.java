package br.dev.hygino.dto;

public record UpdateUserDto(
        long id,
        String name,
        String contact,
        String attribute) {

}
