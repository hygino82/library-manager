package br.dev.hygino.models;

import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Loan {

    private Integer id;
    private Book book;
    private User User;
    private LocalDate startAt = LocalDate.now();
    private LocalDate expiresAt = LocalDate.now().plusDays(7L);
}
