package br.dev.hygino.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.dev.hygino.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("""
        SELECT u FROM User u
        WHERE (:name IS NULL OR :name = '' OR UPPER(u.name) LIKE CONCAT('%', UPPER(:name), '%'))
        """)
    Page<User> findUsersByName(@Param("name") String name, Pageable pageable);

    @Query("""
            SELECT obj FROM User obj WHERE UPPER(obj.email) = UPPER(:email)
            """)
    Optional<User>findByEmail(@Param("email") String email);
}
