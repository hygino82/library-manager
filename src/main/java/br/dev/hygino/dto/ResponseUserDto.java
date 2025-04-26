package br.dev.hygino.dto;

import java.util.List;

import br.dev.hygino.models.User;

public record ResponseUserDto(
    Long id,
    String name,
    String schoolAtribute,
    String email,
    String phoneNumber,
    boolean hasLoan,
    List<ResponseBookDto> loans) {

    public static ResponseUserDto from(User obj) {
        return new ResponseUserDto(
            obj.getId(),
            obj.getName(),
            obj.getSchoolAtribute().name(),
            obj.getEmail(),
            obj.getPhoneNumber(),
            obj.isHasLoan(),
            obj.getBookLoans()
               .stream()
               .map(x -> new ResponseBookDto(x.getBook()))
               .toList() 
        );
    }
}
