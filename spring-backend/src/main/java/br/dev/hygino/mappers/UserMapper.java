package br.dev.hygino.mappers;

import org.mapstruct.Mapper;

import br.dev.hygino.dto.ResponseUserDto;
import br.dev.hygino.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	ResponseUserDto toResponseUserDto(User entity);
}
