package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
