package br.dev.hygino.services;

import br.dev.hygino.dto.BookLoanReportDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class BookLoanServiceTestIT {

    @Autowired
    private BookLoanService bookLoanService;

    private String validEmail, invalidEmail, validPersonalCode, invalidPersonalCode;
    private UUID validUserId, invalidUserid, invalidBookId, validBookId, validLoanId, invalidLoanId;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        validEmail = "rafael.lima@email.com";
        invalidEmail = "invalid.user@email.com";
        validPersonalCode = "BR100125";
        invalidPersonalCode = "INVALID_CODE001";
        validUserId = UUID.fromString("c7b2c61a-ff37-4a76-94ef-9c4d0b701005");
        invalidUserid = UUID.fromString("7d1e12d8-1905-47bc-b261-296bb0b96bb5");
        validBookId = UUID.fromString("b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50111");
        validLoanId = UUID.fromString("d4e39276-9a98-4edf-9fbb-20b2ec700002");
        invalidBookId = invalidLoanId = invalidUserid;
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("Deve retornar uma página com dois empréstimos de livros")
    public void findAllLoansShouldReturnPageWithTwoElements() {
        final Page<BookLoanReportDto> result = bookLoanService.findAllLoans(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals("Vidas Secas", result.getContent().getFirst().bookTitle());
        assertEquals("Juvenal Santos", result.getContent().getFirst().userName());
        assertEquals("Memórias de um Sargento de Milícias", result.getContent().get(1).bookTitle());
        assertEquals("Maria Oliveira", result.getContent().get(1).userName());
    }
}
