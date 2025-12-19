package br.dev.hygino.mappers;

import org.mapstruct.Mapper;

import br.dev.hygino.dto.ResponseBookLoanDto;
import br.dev.hygino.models.BookLoan;

@Mapper(componentModel = "spring")
public interface BookLoanMapper {
	
	ResponseBookLoanDto toBookLoanResponse(BookLoan bookLoan);
}
