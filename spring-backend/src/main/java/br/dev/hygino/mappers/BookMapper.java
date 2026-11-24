package br.dev.hygino.mappers;

import br.dev.hygino.dto.ResponseBookDto;
import org.mapstruct.Mapper;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.dto.ResponseBookDetailsDto;
import br.dev.hygino.models.Book;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "category", source = "category.name")
    ResponseBookDetailsDto toBookResponse(Book book);

    Book toBookEntity(RequestBookDto dto);

    @Mapping(target = "category", source = "category.name")
    ResponseBookDto toBookMinResponse(Book entity);
}
