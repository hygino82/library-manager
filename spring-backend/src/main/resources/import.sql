INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50101', 'AVAILABLE', 1, 192, 'Machado de Assis', 'BR1001', 'Editora Record', 'Dom Casmurro', '2025-01-10 09:23:15', '2025-01-10 09:23:15');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50102', 'AVAILABLE', 3, 368, 'José de Alencar', 'BR1002', 'Editora Ática', 'Iracema', '2025-02-18 14:42:55', '2025-02-18 14:42:55');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50103', 'IN_USE', 2, 224, 'Graciliano Ramos', 'BR1003', 'Editora Record', 'Vidas Secas', '2025-03-05 10:12:33', '2025-03-05 10:12:33');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50104', 'AVAILABLE', 1, 208, 'Clarice Lispector', 'BR1004', 'Rocco', 'A Hora da Estrela', '2025-04-09 16:45:22', '2025-04-09 16:45:22');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50105', 'IN_USE', 1, 160, 'Manuel Antônio de Almeida', 'BR1005', 'Editora Saraiva', 'Memórias de um Sargento de Milícias', '2025-05-13 08:30:00', '2025-05-13 08:30:00');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50106', 'AVAILABLE', 1, 336, 'Jorge Amado', 'BR1006', 'Companhia das Letras', 'Capitães da Areia', '2025-06-02 12:18:10', '2025-06-02 12:18:10');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50107', 'AVAILABLE', 2, 320, 'Euclides da Cunha', 'BR1007', 'Editora Cultrix', 'Os Sertões', '2025-07-14 11:59:44', '2025-07-14 11:59:44');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50108', 'AVAILABLE', 1, 288, 'Raquel de Queiroz', 'BR1008', 'José Olympio', 'O Quinze', '2025-08-22 15:07:01', '2025-08-22 15:07:01');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50109', 'AVAILABLE', 4, 180, 'Aluísio Azevedo', 'BR1009', 'Martin Claret', 'O Cortiço', '2025-09-10 13:36:27', '2025-09-10 13:36:27');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50110', 'AVAILABLE', 1, 272, 'Lima Barreto', 'BR1010', 'Penguin Companhia', 'Triste Fim de Policarpo Quaresma', '2025-10-05 17:22:49', '2025-10-05 17:22:49');
INSERT INTO tb_book (id, BOOK_STATUS, EDITION, TOTAL_PAGES, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES('b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50111', 'AVAILABLE', 1, 247, 'Machado de Assis', 'BR100125', 'Editora Record', 'Quincas Borba', '2025-11-11 10:05:33', '2025-11-11 10:05:33');

INSERT INTO tb_user (id, name, school_attribute, phone_number, email, has_loan) VALUES('c7b2c61a-ff37-4a76-94ef-9c4d0b701001', 'Juvenal Santos', 0, '7823739244', 'juvenal@email.com', true);
INSERT INTO tb_user (id, name, school_attribute, phone_number, email, has_loan) VALUES('c7b2c61a-ff37-4a76-94ef-9c4d0b701002', 'Maria Oliveira', 1, '7812345678', 'maria.oliveira@email.com', true);
INSERT INTO tb_user (id, name, school_attribute, phone_number, email, has_loan) VALUES('c7b2c61a-ff37-4a76-94ef-9c4d0b701003', 'Carlos Souza', 2, '7898765432', 'carlos.souza@email.com', false);
INSERT INTO tb_user (id, name, school_attribute, phone_number, email, has_loan) VALUES('c7b2c61a-ff37-4a76-94ef-9c4d0b701004', 'Ana Pereira', 1, '7823456789', 'ana.pereira@email.com', false);
INSERT INTO tb_user (id, name, school_attribute, phone_number, email, has_loan) VALUES('c7b2c61a-ff37-4a76-94ef-9c4d0b701005', 'Rafael Lima', 0, '7876543210', 'rafael.lima@email.com', false);

INSERT INTO tb_book_loan (id, end_date, start_date, book_id, user_id, active) VALUES('d4e39276-9a98-4edf-9fbb-20b2ec700001', '2025-11-20', '2025-11-10','b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50103', 'c7b2c61a-ff37-4a76-94ef-9c4d0b701001', true);
INSERT INTO tb_book_loan (id, end_date, start_date, book_id, user_id, active) VALUES('d4e39276-9a98-4edf-9fbb-20b2ec700002', '2025-11-20', '2025-11-10', 'b1a8e1ee-4e6b-45e6-9b83-1f1c2fb50105', 'c7b2c61a-ff37-4a76-94ef-9c4d0b701002',  true);

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440001', 'Romance', 'Obras literárias centradas em relações amorosas e afetivas.', '2025-01-10 08:30:00', '2025-01-10 08:30:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440002', 'Ficção Científica', 'Obras que exploram ciência, tecnologia, futuro e seus impactos na sociedade.', '2025-02-15 09:15:00', '2025-02-15 09:15:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440003', 'Fantasia', 'Obras que apresentam elementos mágicos, sobrenaturais ou mundos imaginários.', '2025-03-20 10:00:00', '2025-03-20 10:00:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440004', 'Terror', 'Obras destinadas a provocar medo, suspense ou tensão.', '2025-04-05 14:20:00', '2025-04-05 14:20:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440005', 'Mistério', 'Obras centradas na investigação de acontecimentos desconhecidos, crimes ou enigmas.', '2025-05-12 11:45:00', '2025-05-12 11:45:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440006', 'Aventura', 'Obras que apresentam jornadas, desafios, exploração e situações de ação.', '2025-06-18 13:10:00', '2025-06-18 13:10:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440007', 'Biografia', 'Livros que apresentam a história e a trajetória de vida de uma pessoa.', '2025-07-22 15:30:00', '2025-07-22 15:30:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440008', 'História', 'Obras que abordam acontecimentos, sociedades e períodos históricos.', '2025-08-09 09:40:00', '2025-08-09 09:40:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440009', 'Filosofia', 'Obras relacionadas ao pensamento filosófico, ética, conhecimento e existência.', '2025-09-14 16:00:00', '2025-09-14 16:00:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440010', 'Tecnologia', 'Livros relacionados à computação, tecnologia, inovação e desenvolvimento tecnológico.', '2025-10-03 10:25:00', '2025-10-03 10:25:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440011', 'Educação', 'Obras voltadas ao ensino, aprendizagem, pedagogia e práticas educacionais.', '2025-11-17 08:50:00', '2025-11-17 08:50:00');

INSERT INTO tb_category (id, name, description, created_at, updated_at) VALUES ('550e8400-e29b-41d4-a716-446655440012', 'Matemática', 'Livros relacionados ao estudo da matemática, seus conceitos e aplicações.', '2026-01-25 14:35:00', '2026-01-25 14:35:00');