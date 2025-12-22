/* =========================
   TB_BOOK
   ========================= */

INSERT INTO tb_book (
  id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR,
  PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT
) VALUES
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50101'), 'AVAILABLE', 1, 192, 'Machado de Assis', 'BR1001', 'Editora Record', 'Dom Casmurro', '2025-01-10 09:23:15', '2025-01-10 09:23:15'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50102'), 'AVAILABLE', 3, 368, 'José de Alencar', 'BR1002', 'Editora Ática', 'Iracema', '2025-02-18 14:42:55', '2025-02-18 14:42:55'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50103'), 'IN_USE',    2, 224, 'Graciliano Ramos', 'BR1003', 'Editora Record', 'Vidas Secas', '2025-03-05 10:12:33', '2025-03-05 10:12:33'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50104'), 'AVAILABLE', 1, 208, 'Clarice Lispector', 'BR1004', 'Rocco', 'A Hora da Estrela', '2025-04-09 16:45:22', '2025-04-09 16:45:22'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50105'), 'IN_USE',    1, 160, 'Manuel Antônio de Almeida', 'BR1005', 'Editora Saraiva', 'Memórias de um Sargento de Milícias', '2025-05-13 08:30:00', '2025-05-13 08:30:00'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50106'), 'AVAILABLE', 1, 336, 'Jorge Amado', 'BR1006', 'Companhia das Letras', 'Capitães da Areia', '2025-06-02 12:18:10', '2025-06-02 12:18:10'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50107'), 'AVAILABLE', 2, 320, 'Euclides da Cunha', 'BR1007', 'Editora Cultrix', 'Os Sertões', '2025-07-14 11:59:44', '2025-07-14 11:59:44'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50108'), 'AVAILABLE', 1, 288, 'Raquel de Queiroz', 'BR1008', 'José Olympio', 'O Quinze', '2025-08-22 15:07:01', '2025-08-22 15:07:01'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50109'), 'AVAILABLE', 4, 180, 'Aluísio Azevedo', 'BR1009', 'Martin Claret', 'O Cortiço', '2025-09-10 13:36:27', '2025-09-10 13:36:27'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50110'), 'AVAILABLE', 1, 272, 'Lima Barreto', 'BR1010', 'Penguin Companhia', 'Triste Fim de Policarpo Quaresma', '2025-10-05 17:22:49', '2025-10-05 17:22:49'),
(UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50111'), 'AVAILABLE', 1, 247, 'Machado de Assis', 'BR100125', 'Editora Record', 'Quincas Borba', '2025-11-11 10:05:33', '2025-11-11 10:05:33');


/* =========================
   TB_USER
   ========================= */

INSERT INTO tb_user (
  id, name, school_attribute, phone_number, email, has_loan
) VALUES
(UUID_TO_BIN('c7b2c61a-ff37-4a76-94ef-9c4d0b701001'), 'Juvenal Santos', 0, '7823739244', 'juvenal@email.com', true),
(UUID_TO_BIN('c7b2c61a-ff37-4a76-94ef-9c4d0b701002'), 'Maria Oliveira', 1, '7812345678', 'maria.oliveira@email.com', true),
(UUID_TO_BIN('c7b2c61a-ff37-4a76-94ef-9c4d0b701003'), 'Carlos Souza', 2, '7898765432', 'carlos.souza@email.com', false),
(UUID_TO_BIN('c7b2c61a-ff37-4a76-94ef-9c4d0b701004'), 'Ana Pereira', 1, '7823456789', 'ana.pereira@email.com', false),
(UUID_TO_BIN('c7b2c61a-ff37-4a76-94ef-9c4d0b701005'), 'Rafael Lima', 0, '7876543210', 'rafael.lima@email.com', false);


/* =========================
   TB_BOOK_LOAN
   ========================= */

INSERT INTO tb_book_loan (
  id, end_date, start_date, book_id, user_id, active
) VALUES
(
  UUID_TO_BIN('d4e39276-9a98-4edf-9fbb-20b2ec700001'),
  '2025-11-20',
  '2025-11-10',
  UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50103'),
  UUID_TO_BIN('c7b2c61a-ff37-4a76-94ef-9c4d0b701001'),
  true
),
(
  UUID_TO_BIN('d4e39276-9a98-4edf-9fbb-20b2ec700002'),
  '2025-11-20',
  '2025-11-10',
  UUID_TO_BIN('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50105'),
  UUID_TO_BIN('c7b2c61a-ff37-4a76-94ef-9c4d0b701002'),
  true
);

