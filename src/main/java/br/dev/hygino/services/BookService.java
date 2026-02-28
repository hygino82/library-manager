package br.dev.hygino.services;

import br.dev.hygino.dao.BookDao;
import br.dev.hygino.dto.InsertBookDto;
import br.dev.hygino.dto.ResponseBookDto;
import br.dev.hygino.exceptions.ResourceNotFoundException;
import java.util.List;

public class BookService {

    private final BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public void insertBook(InsertBookDto dto) {
        bookDao.insertBook(dto);
    }

    public List<ResponseBookDto> getBooks(String title) {
        try {
            return bookDao.findBooks(title)
                    .stream()
                    .map(ResponseBookDto::new)
                    .toList();
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }
}
