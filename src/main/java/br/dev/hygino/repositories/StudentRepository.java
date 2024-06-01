package br.dev.hygino.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.dev.hygino.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("""
            SELECT obj FROM Student obj WHERE obj.id = :id
            """)
    Optional<Student> findStudentById(Long id);

}
