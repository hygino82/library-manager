package br.dev.hygino.services;

import jakarta.persistence.EntityNotFoundException;
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
        return page.map(BookDTO::new);
    }

    @Transactional(readOnly = true)
    public BookDTO findById(Long id) {
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book id: " + id + " not found"));
        return new BookDTO(entity);
    }

    @Transactional
    public BookDTO update(Long id, InsertBookDTO dto) {
        try {
            Book entity = bookRepository.getReferenceById(id);
            entity = bookRepository.save(copyDtoToEntity(entity, dto));
            return new BookDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Book with id: " + id + " not found");
        }
    }

    private Book copyDtoToEntity(Book entity, InsertBookDTO dto) {
        entity.setTitle(dto.title());
        entity.setIsbn(dto.isbn());
        entity.setPublisher(dto.publisher());
        entity.setReleaseYear(dto.year());
        entity.setLeftAmount(dto.leftAmount());
        entity.setTotalAmount(dto.totalAmount());
        return entity;
    }
}
