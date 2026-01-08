package br.dev.hygino.mappers;

import br.dev.hygino.dto.MinBookLoanResponseDto;
import br.dev.hygino.dto.ResponseBookLoanDto;
import br.dev.hygino.models.BookLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookLoanMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "book.title", target = "bookTitle")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.bookStatus", target = "bookStatus")
    @Mapping(source = "user.hasLoan", target = "userHasLoan")
    @Mapping(source = "active", target = "hasActiveLoan")
    ResponseBookLoanDto toBookLoanResponse(BookLoan bookLoan);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "book.title", target = "title")
    @Mapping(source = "user.name", target = "username")
    @Mapping(source = "startDate", target = "startAt")
    MinBookLoanResponseDto toBookMinLoanResponse(BookLoan bookLoan);
}
