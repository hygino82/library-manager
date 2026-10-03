package br.dev.hygino.services;

import br.dev.hygino.dto.RequestCategoryDto;
import br.dev.hygino.dto.ResponseCategoryDto;
import br.dev.hygino.factories.CategoryFactory;
import br.dev.hygino.models.Category;
import br.dev.hygino.repositories.CategoryRepository;
import br.dev.hygino.services.exceptions.ResourceNotFoundexception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    private Category newCategory;
    private RequestCategoryDto categoryRequest;
    private UUID nonExistingId, existingId;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        newCategory = CategoryFactory.createNewCategory();
        categoryRequest = CategoryFactory.createNewRequestCategoryDto();
        existingId = UUID.fromString(
                "550e8400-e29b-41d4-a716-446655440012"
        );
        nonExistingId = UUID.fromString(
                "d2fb7df6-d20a-4956-83a3-2972a04b5f2d"
        );
    }

    @Test
    @DisplayName("Deve retornar uma categoria quando os dados forem válidos")
    void insertShouldReturnCategoryWhenDataIsValid() {
        when(categoryRepository.save(any())).thenReturn(newCategory);

        final ResponseCategoryDto res =
                categoryService.insert(categoryRequest);

        assertNotNull(res);
        assertEquals(existingId, res.id());
        assertEquals("Matemática", res.name());
        assertEquals(
                "Livros relacionados ao estudo da matemática, seus conceitos e aplicações",
                res.description()
        );
        assertEquals(res.createdAt(), res.updatedAt());
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando o id da categoria não existir")
    void shouldThrowExceptionWhenInvalidCategoryId() {
        when(categoryRepository.findById(nonExistingId))
                .thenReturn(Optional.empty());

        final var res = Assertions.assertThrows(
                ResourceNotFoundexception.class,
                () -> categoryService.findById(nonExistingId)
        );

        assertEquals("Category not found", res.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma categoria quando o id existir")
    void shouldReturnCategoryWhenExistingId() {
        when(categoryRepository.findById(existingId))
                .thenReturn(Optional.of(newCategory));

        final var res = categoryService.findById(existingId);

		assertEquals("Matemática", res.name());
		assertEquals(
				"Livros relacionados ao estudo da matemática, seus conceitos e aplicações",
				res.description()
		);
		assertEquals(res.createdAt(), res.updatedAt());
    }
}