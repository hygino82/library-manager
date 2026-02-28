package br.dev.hygino.dto;

import br.dev.hygino.models.User;

public record ResponseUserMinDto(
        Long id,
        String name,
        String attribute,
        boolean acitiveLoan
        ) {

    public ResponseUserMinDto(User user) {
        this(
                user.getId(), 
                user.getName(), 
                user.getAttribute(), 
                user.isAcitiveLoan()
        );
    }
}
