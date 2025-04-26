package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.models.BookStatus;
import br.dev.hygino.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Transactional(readOnly = true)
	public Page<ResponseBookDto> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable).map(ResponseBookDto::new);
	}

	@Transactional(readOnly = true)
	public ResponseBookDto findById(Long id) {
		final Book res = bookRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Não existe livro com o id: " + id));
		return new ResponseBookDto(res);
	}

	@Transactional
	public ResponseBookDto returnBook(Long id) {
		try {
			Book res = bookRepository.getReferenceById(id);
			res.setBookStatus(BookStatus.AVALIABLE);
			res = bookRepository.save(res);
			return new ResponseBookDto(res);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Não encontrodo Livro com id: " + id);
		}
	}

	@Transactional
	public ResponseBookDto insert(RequestBookDto dto) {
		Book entity = new Book();
		dtoToEntity(dto, entity);
		entity = bookRepository.save(entity);
		return new ResponseBookDto(entity);
	}

	private void dtoToEntity(RequestBookDto dto, Book entity) {
		entity.setTitle(dto.title());
		entity.setAuthor(dto.author());
		entity.setPersonalCode(dto.personalCode());
		entity.setEdition(dto.edition());
		entity.setPublisher(dto.publisher());
		entity.setTotalPages(dto.totalPages());
	}

	@Transactional
	public ResponseBookDto update(Long id, RequestBookDto dto) {
		try {
			Book entity = bookRepository.getReferenceById(id);
			dtoToEntity(dto, entity);
			entity = bookRepository.save(entity);
			return new ResponseBookDto(entity);
		} catch (EntityNotFoundException e) {
			throw new IllegalArgumentException("Não encontrodo Livro com id: " + id);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void remove(Long id) {
		bookRepository.deleteById(id);

	}
}
