package br.dev.hygino.dto;

import java.time.LocalDateTime;

public record BookReportDto(
        Long totalBooks,
        Long availableBooks,
        Long borrowedBooks,
        LocalDateTime searchDate
) {
}
