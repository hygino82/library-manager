package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.entities.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

}
