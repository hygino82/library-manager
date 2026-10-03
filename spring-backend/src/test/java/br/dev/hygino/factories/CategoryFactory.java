package br.dev.hygino.factories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import br.dev.hygino.dto.RequestCategoryDto;
import br.dev.hygino.models.Category;

public class CategoryFactory {
	private static final LocalDateTime createdAt = LocalDateTime.of(2026, 10, 2, 19, 41);

	private static final LocalDateTime updatedAt = LocalDateTime.of(2026, 10, 2, 19, 50);

	private static final UUID newid = UUID.fromString("550e8400-e29b-41d4-a716-446655440012");

	public static RequestCategoryDto createNewRequestCategoryDto() {
		return new RequestCategoryDto("Matemática",
				"Livros relacionados ao estudo da matemática, seus conceitos e aplicações");
	}

	public static Category createNewCategory() {
		return new Category(newid, "Matemática", "Livros relacionados ao estudo da matemática, seus conceitos e aplicações", createdAt, createdAt);
	}

	public static Category updatedCategory = new Category(newid, "Matemática básica", "Operações de matemática básica",
			createdAt, updatedAt);

	public static List<Category> getCategories() {
		return List.of(
				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"), "Romance",
						"Obras literárias centradas em relações amorosas e afetivas.",
						LocalDateTime.of(2025, 1, 10, 8, 30, 0), LocalDateTime.of(2025, 1, 10, 8, 30, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"), "Ficção Científica",
						"Obras que exploram ciência, tecnologia, futuro e seus impactos na sociedade.",
						LocalDateTime.of(2025, 2, 15, 9, 15, 0), LocalDateTime.of(2025, 2, 15, 9, 15, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440003"), "Fantasia",
						"Obras que apresentam elementos mágicos, sobrenaturais ou mundos imaginários.",
						LocalDateTime.of(2025, 3, 20, 10, 0, 0), LocalDateTime.of(2025, 3, 20, 10, 0, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440004"), "Terror",
						"Obras destinadas a provocar medo, suspense ou tensão.",
						LocalDateTime.of(2025, 4, 5, 14, 20, 0), LocalDateTime.of(2025, 4, 5, 14, 20, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440005"), "Mistério",
						"Obras centradas na investigação de acontecimentos desconhecidos, crimes ou enigmas.",
						LocalDateTime.of(2025, 5, 12, 11, 45, 0), LocalDateTime.of(2025, 5, 12, 11, 45, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440006"), "Aventura",
						"Obras que apresentam jornadas, desafios, exploração e situações de ação.",
						LocalDateTime.of(2025, 6, 18, 13, 10, 0), LocalDateTime.of(2025, 6, 18, 13, 10, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440007"), "Biografia",
						"Livros que apresentam a história e a trajetória de vida de uma pessoa.",
						LocalDateTime.of(2025, 7, 22, 15, 30, 0), LocalDateTime.of(2025, 7, 22, 15, 30, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440008"), "História",
						"Obras que abordam acontecimentos, sociedades e períodos históricos.",
						LocalDateTime.of(2025, 8, 9, 9, 40, 0), LocalDateTime.of(2025, 8, 9, 9, 40, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440009"), "Filosofia",
						"Obras relacionadas ao pensamento filosófico, ética, conhecimento e existência.",
						LocalDateTime.of(2025, 9, 14, 16, 0, 0), LocalDateTime.of(2025, 9, 14, 16, 0, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440010"), "Tecnologia",
						"Livros relacionados à computação, tecnologia, inovação e desenvolvimento tecnológico.",
						LocalDateTime.of(2025, 10, 3, 10, 25, 0), LocalDateTime.of(2025, 10, 3, 10, 25, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440011"), "Educação",
						"Obras voltadas ao ensino, aprendizagem, pedagogia e práticas educacionais.",
						LocalDateTime.of(2025, 11, 17, 8, 50, 0), LocalDateTime.of(2025, 11, 17, 8, 50, 0)),

				new Category(UUID.fromString("550e8400-e29b-41d4-a716-446655440012"), "Matemática",
						"Livros relacionados ao estudo da matemática, seus conceitos e aplicações.",
						LocalDateTime.of(2026, 1, 25, 14, 35, 0), LocalDateTime.of(2026, 1, 25, 14, 35, 0)));
	}
}
