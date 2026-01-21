package br.dev.hygino.mappers;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.dto.ResponseBookDetailsDto;
import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.models.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    ResponseBookDetailsDto toBookResponse(Book book);

    Book toBookEntity(RequestBookDto dto);

    ResponseBookDto toBookMinResponse(Book entity);
}