package br.dev.hygino.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.BookDTO;
import br.dev.hygino.dto.BookInsertDTO;
import br.dev.hygino.entities.Book;
import br.dev.hygino.repositories.BookRepository;
import br.dev.hygino.services.exceptions.BookNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public BookDTO insert(@Valid BookInsertDTO dto) {
        Book entity = new Book();
        copyDtoToEntity(dto, entity);
        return new BookDTO(bookRepository.save(entity));
    }

    private void copyDtoToEntity(@Valid BookInsertDTO dto, Book entity) {
        entity.setTitle(dto.title());
        entity.setAuthor(dto.author());
        entity.setPublisher(dto.publisher());
        entity.setReleaseYear(dto.year());
        entity.setAmount(dto.amount());
        entity.setLocation(dto.location());
        entity.setIsbn(dto.isbn());
        entity.setGender(dto.gender());
    }

    @Transactional(readOnly = true)
    public Page<BookDTO> findAll(Pageable pageable) {
        Page<Book> page = bookRepository.findAll(pageable);
        return page.map(BookDTO::new);
    }

    @Transactional(readOnly = true)
    public BookDTO findById(Long id) {
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Livro com o Id: " + id + " não encontrado"));
        return new BookDTO(entity);
    }

    @Transactional
    public BookDTO update(Long id, @Valid BookInsertDTO dto) {
        try {
            Book entity = bookRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            return new BookDTO(bookRepository.save(entity));
        } catch (EntityNotFoundException e) {
            throw new BookNotFoundException("Livro com o Id: " + id + " não encontrado");
        }
    }

    public void remove(Long id) {
        Book Book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(
                        "Impossível remover o Livro com o Id: " + id + ", pois ele não existe"));
        bookRepository.delete(Book);
    }
}
