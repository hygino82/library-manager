package br.dev.hygino.mappers;

import org.mapstruct.Mapper;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.dto.ResponseBookDetailsDto;
import br.dev.hygino.models.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    ResponseBookDetailsDto toBookResponse(Book book);

    Book toBookEntity(RequestBookDto dto);

    //ResponseBookDto toBookMinResponse(Book entity);
}
