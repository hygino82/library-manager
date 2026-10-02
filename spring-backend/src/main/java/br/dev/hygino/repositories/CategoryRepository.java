package br.dev.hygino.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.models.Category;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

}
