package br.dev.hygino.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}
