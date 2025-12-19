package br.dev.hygino.mappers;

import br.dev.hygino.dto.ResponseMinUserDto;
import org.mapstruct.Mapper;

import br.dev.hygino.dto.ResponseUserDto;
import br.dev.hygino.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ResponseUserDto toResponseUserDto(User entity);

    ResponseMinUserDto toResponseMinUserDto(User entity);
}
