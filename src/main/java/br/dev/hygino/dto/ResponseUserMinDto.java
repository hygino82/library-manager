package br.dev.hygino.dto;

import br.dev.hygino.models.User;

public record ResponseUserMinDto(
        Long id,
        String name,
        String contact,
        String attribute,
        boolean acitiveLoan
        ) {

    public ResponseUserMinDto(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getContact(),
                user.getAttribute(),
                user.isAcitiveLoan()
        );
    }
}
