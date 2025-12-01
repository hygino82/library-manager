INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (1, 1, 192, 1, 'Machado de Assis', 'BR1001', 'Editora Record', 'Dom Casmurro', '2025-01-10 09:23:15', '2025-01-10 09:23:15');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (1, 3, 368, 2, 'José de Alencar', 'BR1002', 'Editora Ática', 'Iracema', '2025-02-18 14:42:55', '2025-02-18 14:42:55');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (3, 2, 224, 3, 'Graciliano Ramos', 'BR1003', 'Editora Record', 'Vidas Secas', '2025-03-05 10:12:33', '2025-03-05 10:12:33');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (1, 1, 208, 4, 'Clarice Lispector', 'BR1004', 'Rocco', 'A Hora da Estrela', '2025-04-09 16:45:22', '2025-04-09 16:45:22');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (3, 1, 160, 5, 'Manuel Antônio de Almeida', 'BR1005', 'Editora Saraiva', 'Memórias de um Sargento de Milícias', '2025-05-13 08:30:00', '2025-05-13 08:30:00');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (1, 1, 336, 6, 'Jorge Amado', 'BR1006', 'Companhia das Letras', 'Capitães da Areia', '2025-06-02 12:18:10', '2025-06-02 12:18:10');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (1, 2, 320, 7, 'Euclides da Cunha', 'BR1007', 'Editora Cultrix', 'Os Sertões', '2025-07-14 11:59:44', '2025-07-14 11:59:44');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (1, 1, 288, 8, 'Raquel de Queiroz', 'BR1008', 'José Olympio', 'O Quinze', '2025-08-22 15:07:01', '2025-08-22 15:07:01');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (1, 4, 180, 9, 'Aluísio Azevedo', 'BR1009', 'Martin Claret', 'O Cortiço', '2025-09-10 13:36:27', '2025-09-10 13:36:27');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (1, 1, 272, 10, 'Lima Barreto', 'BR1010', 'Penguin Companhia', 'Triste Fim de Policarpo Quaresma', '2025-10-05 17:22:49', '2025-10-05 17:22:49');
INSERT INTO tb_book (BOOK_STATUS, EDITION, TOTAL_PAGES, ID, AUTHOR, PERSONAL_CODE, PUBLISHER, TITLE, CREATED_AT, UPDATED_AT) VALUES (1, 1, 247, 11, 'Machado de Assis', 'BR100125', 'Editora Record', 'Quincas Borba', '2025-11-11 10:05:33', '2025-11-11 10:05:33');


INSERT INTO tb_user (name, school_atribute, phone_number, email, has_loan) VALUES ('Juvenal Santos', 0, '7823739244', 'juvenal@email.com', true);
INSERT INTO tb_user (name, school_atribute, phone_number, email, has_loan) VALUES ('Maria Oliveira', 1, '7812345678', 'maria.oliveira@email.com', true);
INSERT INTO tb_user (name, school_atribute, phone_number, email, has_loan) VALUES ('Carlos Souza', 2, '7898765432', 'carlos.souza@email.com', false);
INSERT INTO tb_user (name, school_atribute, phone_number, email, has_loan) VALUES ('Ana Pereira', 1, '7823456789', 'ana.pereira@email.com', false);
INSERT INTO tb_user (name, school_atribute, phone_number, email, has_loan) VALUES ('Rafael Lima', 0, '7876543210', 'rafael.lima@email.com', false);

insert into tb_book_loan(end_date, start_date, book_id, user_id, active) values ('2025-11-20',	'2025-11-10', 3, 1, true);
insert into tb_book_loan(end_date, start_date, book_id, user_id, active) values ('2025-11-20',	'2025-11-10', 5, 2, true);