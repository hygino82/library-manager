package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.BookDTO;
import br.dev.hygino.dto.InsertBookDTO;
import br.dev.hygino.entities.Book;
import br.dev.hygino.repositories.BookRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
	private final BookRepository bookRepository;

	@Transactional
	public BookDTO insert(InsertBookDTO dto) {
		Book entity = bookRepository.save(new Book(dto));
		return new BookDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<BookDTO> findAll(Pageable pageable) {
		Page<Book> page = bookRepository.findAll(pageable);
		return page.map(x -> new BookDTO(x));
	}
}
