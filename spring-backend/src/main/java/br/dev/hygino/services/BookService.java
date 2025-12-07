package br.dev.hygino.services;

import br.dev.hygino.dto.BookReportDto;
import br.dev.hygino.mappers.BookMapper;
import br.dev.hygino.models.BookStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestBookDto;
import br.dev.hygino.dto.ResponseBookDetailsDto;
import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.models.Book;
import br.dev.hygino.repositories.BookRepository;
import br.dev.hygino.services.exceptions.BorrowBookException;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional(readOnly = true)
    public Page<ResponseBookDto> findAll(String title, String author, Pageable pageable) {
        return bookRepository.findBooksByTitleAndAuthor(title, author, pageable).map(ResponseBookDto::new);
    }

    @Transactional(readOnly = true)
    public ResponseBookDetailsDto findById(UUID id) {
        final Book res = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Não existe livro com o id: " + id));
        return bookMapper.toBookResponse(res);
    }

    @Transactional
    public ResponseBookDto insert(RequestBookDto dto) {
        Book entity=bookMapper.toBookEntity(dto);
        //Book entity = new Book();
        //dtoToEntity(dto, entity);
        entity = bookRepository.save(entity);
        return bookMapper.toBookMinResponse(entity);
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
    public ResponseBookDto update(UUID id, RequestBookDto dto) {
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
    public void remove(UUID id) {
        try {
            bookRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new BorrowBookException("Não pode excluir um livro com empréstimo");
        }
    }

    @Transactional(readOnly = true)
    public BookReportDto getBookReport() {
        final var res = this.bookRepository.findAll();
        final var totalBooks = (long) res.size();
        final var availableBooks = res.stream().filter(book -> book.getBookStatus() == BookStatus.AVAILABLE).count();
        final var borrowedBooks = res.stream().filter(book -> book.getBookStatus() == BookStatus.IN_USE).count();

        return new BookReportDto(totalBooks, availableBooks, borrowedBooks, LocalDateTime.now());
    }
}
