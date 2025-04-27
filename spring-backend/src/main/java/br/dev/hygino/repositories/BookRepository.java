package br.dev.hygino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.dev.hygino.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    /*  @Query("""
        SELECT u FROM Title u
        WHERE (:title IS NULL OR :title = '' OR UPPER(u.title) LIKE CONCAT('%', UPPER(:title), '%'))
        """)
    Page<Book> findBooksByTile(@Param("title") String title, Pageable pageable);*/
}
