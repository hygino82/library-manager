package br.dev.hygino.dto;

import br.dev.hygino.entities.Student;

public record StudentDTO(
        Long id,
        String name,
        String address,
        String phone,
        String email,
        String className) {

    public StudentDTO(Student entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getAddress(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getClassName()
            );
    }
}
