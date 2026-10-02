package br.dev.hygino.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<I, O> {
	O insert(I dto);

	Page<O> findAll(Pageable pageable);

	O update(UUID id, I dto);

	O findById(UUID id);

	void delete(UUID id);
}
