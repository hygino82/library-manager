package br.dev.hygino.services;

import br.dev.hygino.dto.RequestCategoryDto;
import br.dev.hygino.dto.ResponseCategoryDto;
import br.dev.hygino.factories.CategoryFactory;
import br.dev.hygino.services.exceptions.DatabaseException;
import br.dev.hygino.services.exceptions.ResourceNotFoundexception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class CategoryServiceIT {
    private RequestCategoryDto categoryRequest;

    private UUID invalidId, validId, dependentId;

    @Autowired
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        invalidId = UUID.fromString("4d39e0c2-1d31-4ef5-b1c5-4b829d0763b3");
        validId = UUID.fromString("550e8400-e29b-41d4-a716-446655440012");
        dependentId = UUID.fromString("550e8400-e29b-41d4-a716-446655440001");
        categoryRequest = CategoryFactory.createNewRequestCategoryDto();
    }

    @Test
    @DisplayName("Deve retornar uma categoria quando os dados forem válidos")
    void insertShouldReturnCategoryWhenDataIsValid() {
        final ResponseCategoryDto res = categoryService.insert(categoryRequest);

        assertNotNull(res);
        assertEquals("Matemática", res.name());
        assertEquals("Livros relacionados ao estudo da matemática, seus conceitos e aplicações", res.description());
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando o id da categoria não existir")
    void shouldThrowExceptionWhenInvalidCategoryId() {
        final var res = Assertions.assertThrows(ResourceNotFoundexception.class,
                () -> categoryService.findById(invalidId));

        assertEquals("Category not found", res.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma categoria quando o id existir")
    void shouldReturnCategoryWhenExistingId() {
        final var res = categoryService.findById(validId);

        assertEquals("Matemática", res.name());
        assertEquals("Livros relacionados ao estudo da matemática, seus conceitos e aplicações.", res.description());
        assertEquals(res.createdAt(), res.updatedAt());
    }

    @Test
    @DisplayName("Deve retornar uma página com 12 elementos")
    public void findAllShouldReturnPage() {
        var pageable = PageRequest.of(0, 12);
        final var res = categoryService.findAll(pageable);

        assertNotNull(res);
        assertEquals(12, res.getNumberOfElements());
        assertEquals("Romance", res.getContent().getFirst().name());
        assertEquals("Matemática", res.getContent().getLast().name());
    }

    @Test
    @DisplayName("Deve lançar DatabaseException ao tentar remover uma categoria que tenha livros associados")
    void deleteShouldThrowDatabaseExceptionWhenCategoryHasBooks() {
        final var res = Assertions.assertThrows(DatabaseException.class,
                () -> categoryService.delete(dependentId));

        assertEquals("Integrity violation", res.getMessage());
    }
}
