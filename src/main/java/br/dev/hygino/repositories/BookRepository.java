package br.dev.hygino.repositories;

import br.dev.hygino.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
