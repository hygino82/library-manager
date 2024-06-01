package br.dev.hygino.dto;

import br.dev.hygino.entities.Staff;

public record StaffDTO(
        Long id,
        String name,
        String position,
        String phone,
        String email) {
            
    public StaffDTO(Staff entity) {
        this(
                entity.getId(),
                entity.getName(),
                entity.getPosition(),
                entity.getPhone(),
                entity.getEmail()
            );
    }
}
