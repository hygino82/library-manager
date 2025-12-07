package br.dev.hygino.repositories;

import br.dev.hygino.models.Book;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("""
    SELECT u FROM Book u
    WHERE (:title IS NULL OR :title = '' OR UPPER(u.title) LIKE CONCAT('%', UPPER(:title), '%'))
        AND
        (:author IS NULL OR :author = '' OR UPPER(u.author) LIKE CONCAT('%', UPPER(:author), '%'))
    """)
    Page<Book> findBooksByTitleAndAuthor(
            @Param("title") String title,
            @Param("author") String author,
            Pageable pageable);

}
