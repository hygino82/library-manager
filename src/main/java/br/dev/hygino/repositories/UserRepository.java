package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
