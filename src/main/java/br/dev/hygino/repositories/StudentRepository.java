package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.entities.Student;

public interface StudentRepository extends JpaRepository<Student,Long> {

}
