package br.dev.hygino.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.dev.hygino.dto.RequestCategoryDto;
import br.dev.hygino.dto.ResponseCategoryDto;
import br.dev.hygino.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public ResponseEntity<Page<ResponseCategoryDto>> getAllCategories(Pageable pageable) {
		return ResponseEntity.ok(categoryService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseCategoryDto> getCategoryById(@PathVariable UUID id) {
		return ResponseEntity.ok(categoryService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ResponseCategoryDto> createCategory(@RequestBody @Valid RequestCategoryDto dto) {
		return ResponseEntity.ok(categoryService.insert(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseCategoryDto> updateCategory(@PathVariable UUID id,
			@RequestBody @Valid RequestCategoryDto dto) {
		return ResponseEntity.ok(categoryService.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
