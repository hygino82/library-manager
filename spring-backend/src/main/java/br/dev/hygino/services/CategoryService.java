package br.dev.hygino.services;

import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.dto.RequestCategoryDto;
import br.dev.hygino.dto.ResponseCategoryDto;
import br.dev.hygino.models.Category;
import br.dev.hygino.repositories.CategoryRepository;
import br.dev.hygino.services.exceptions.DatabaseException;
import br.dev.hygino.services.exceptions.ResourceNotFoundexception;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements IService<RequestCategoryDto, ResponseCategoryDto> {
	private final CategoryRepository repository;

	@Override
	@Transactional
	public ResponseCategoryDto insert(RequestCategoryDto dto) {
		Category category = new Category();
		dtoToEntity(dto, category);
		category = repository.save(category);

		return new ResponseCategoryDto(category);
	}

	private void dtoToEntity(RequestCategoryDto dto, Category entity) {
		entity.setName(dto.name());
		entity.setDescription(dto.description());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ResponseCategoryDto> findAll(Pageable pageable) {
		return repository.findAll(pageable).map(ResponseCategoryDto::new);
	}

	@Override
	public ResponseCategoryDto update(UUID id, RequestCategoryDto dto) {
		try {
			var category = repository.getReferenceById(id);
			dtoToEntity(dto, category);
			category = repository.save(category);
			return new ResponseCategoryDto(category);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundexception("Category not found");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseCategoryDto findById(UUID id) {
		return repository.findById(id)
				.map(ResponseCategoryDto::new)
				.orElseThrow(() -> new ResourceNotFoundexception("Category not found"));
	}

	@Override
	public void delete(UUID id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
}
