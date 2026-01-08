package br.dev.hygino.dto;

import java.util.List;
import java.util.UUID;

import br.dev.hygino.models.User;

public record ResponseUserDto(
        UUID id,
        String name,
        String schoolAttribute,
        String email,
        String phoneNumber,
        boolean hasLoan,
        List<MinBookLoanResponseDto> loans) {

    public static ResponseUserDto from(User obj) {
        return new ResponseUserDto(
                obj.getId(),
                obj.getName(),
                obj.getSchoolAttribute().name(),
                obj.getEmail(),
                obj.getPhoneNumber(),
                obj.isHasLoan(),
                obj.getBookLoans()
                        .stream()
                        .map(x -> new MinBookLoanResponseDto(
                                x.getId(),
                                x.getBook().getTitle(),
                                x.getUser().getName(),
                                x.getStartDate()))
                        .toList()
        );
    }
}
