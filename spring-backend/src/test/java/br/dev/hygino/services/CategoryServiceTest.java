package br.dev.hygino.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.dev.hygino.services.exceptions.DatabaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.dev.hygino.dto.RequestCategoryDto;
import br.dev.hygino.dto.ResponseCategoryDto;
import br.dev.hygino.factories.CategoryFactory;
import br.dev.hygino.models.Category;
import br.dev.hygino.repositories.CategoryRepository;
import br.dev.hygino.services.exceptions.ResourceNotFoundexception;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    private Category newCategory;
    private RequestCategoryDto categoryRequest;
    private UUID nonExistingId, existingId, dependentId;
    private Page<Category> page;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setup() {
        newCategory = CategoryFactory.createNewCategory();
        categoryRequest = CategoryFactory.createNewRequestCategoryDto();
        existingId = UUID.fromString("550e8400-e29b-41d4-a716-446655440012");
        nonExistingId = UUID.fromString("d2fb7df6-d20a-4956-83a3-2972a04b5f2d");
        dependentId = UUID.fromString("87dd1135-5831-42c7-a905-dc1964302480");

        final List<Category> categories = CategoryFactory.getCategories();

        page = new PageImpl<>(categories, PageRequest.of(0, 12), categories.size());
    }

    @Test
    @DisplayName("Deve retornar uma categoria quando os dados forem válidos")
    void insertShouldReturnCategoryWhenDataIsValid() {
        when(categoryRepository.save(any())).thenReturn(newCategory);

        final ResponseCategoryDto res = categoryService.insert(categoryRequest);

        assertNotNull(res);
        assertEquals(existingId, res.id());
        assertEquals("Matemática", res.name());
        assertEquals("Livros relacionados ao estudo da matemática, seus conceitos e aplicações", res.description());
        assertEquals(res.createdAt(), res.updatedAt());
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando o id da categoria não existir")
    void shouldThrowExceptionWhenInvalidCategoryId() {
        when(categoryRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        final var res = Assertions.assertThrows(ResourceNotFoundexception.class,
                () -> categoryService.findById(nonExistingId));

        assertEquals("Category not found", res.getMessage());
    }

    @Test
    @DisplayName("Deve retornar uma categoria quando o id existir")
    void shouldReturnCategoryWhenExistingId() {
        when(categoryRepository.findById(existingId)).thenReturn(Optional.of(newCategory));

        final var res = categoryService.findById(existingId);

        assertEquals("Matemática", res.name());
        assertEquals("Livros relacionados ao estudo da matemática, seus conceitos e aplicações", res.description());
        assertEquals(res.createdAt(), res.updatedAt());
    }

    @Test
    @DisplayName("Deve retornar uma página com 12 elementos")
    public void findAllHouldReturnPage() {
        var pageable = PageRequest.of(0, 12);
        when(categoryRepository.findAll(pageable)).thenReturn(page);

        final var res = categoryService.findAll(pageable);

        assertNotNull(res);
        assertEquals(12, res.getNumberOfElements());
        assertEquals("Romance", res.getContent().getFirst().name());
        assertEquals("Matemática", res.getContent().getLast().name());
    }

    @Test
    @DisplayName("Deve remover uma categoria que não tenha livros associados")
    void deleteShouldRemoveCategoryWithoutBooks() {

        categoryService.delete(existingId);

        verify(categoryRepository).deleteById(existingId);
    }

    @Test
    @DisplayName("Deve lançar DatabaseException ao tentar remover uma categoria que tenha livros associados")
    void deleteShouldThrowDataIntegrityViolationException() {

        doThrow(DataIntegrityViolationException.class).when(categoryRepository).deleteById(dependentId);

        final var res = Assertions.assertThrows(DatabaseException.class,
                () -> categoryService.delete(dependentId));

        assertEquals("Integrity violation", res.getMessage());
    }

	@Test
	@DisplayName("Deve atualizar uma categoria quando os dados forem válidos")
	void updateShouldReturnCategoryWhenValidData() {

		final var existingCategory = CategoryFactory.createNewCategory();
		final var updatedCategory = CategoryFactory.createUpdatedRequestCategoryDto();

		when(categoryRepository.getReferenceById(existingId))
				.thenReturn(existingCategory);

		when(categoryRepository.save(any(Category.class)))
				.thenReturn(CategoryFactory.updatedCategory);

		final var res = categoryService.update(
				existingId,
				CategoryFactory.createUpdatedRequestCategoryDto()
		);

		assertEquals("Matemática básica", res.name());
		assertEquals(existingId, res.id());

		verify(categoryRepository).getReferenceById(existingId);
		verify(categoryRepository).save(any(Category.class));
	}
}