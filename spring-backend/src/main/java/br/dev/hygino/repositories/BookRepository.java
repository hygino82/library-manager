package br.dev.hygino.repositories;

import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
	@Query("""
		    SELECT u FROM Book u
		    WHERE (:title IS NULL OR :title = '' 
		           OR UPPER(u.title) LIKE CONCAT('%', UPPER(:title), '%'))
		      AND (:author IS NULL OR :author = '' 
		           OR UPPER(u.author) LIKE CONCAT('%', UPPER(:author), '%'))
		      AND (:bookStatus IS NULL OR u.bookStatus = :bookStatus)
		""")
		Page<Book> findBooksByTitleAndAuthor(
		        @Param("title") String title,
		        @Param("author") String author,
		        @Param("bookStatus") BookStatus bookStatus,
		        Pageable pageable
		);

	@Query("""
			SELECT obj FROM Book obj WHERE UPPER(obj.personalCode) = UPPER(:code)
			""")
	Optional<Book> findByPersonalCode(@Param("code") String code);
}
