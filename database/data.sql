create schema biblioteca;

CREATE TABLE IF NOT EXISTS tb_user
(
    id
    BIGINT
    UNSIGNED
    NOT
    NULL
    AUTO_INCREMENT,
    name
    VARCHAR
(
    100
) NOT NULL,
    attribute VARCHAR
(
    20
) NOT NULL,
    active_loan BOOLEAN NOT NULL DEFAULT FALSE,
    contact VARCHAR
(
    50
) NOT NULL,
    PRIMARY KEY
(
    id
)
    ) ENGINE=InnoDB;

INSERT INTO tb_user(name, attribute, contact)
VALUES ('Juvenal Cardoso', 'SEXTO', '469874182329');
INSERT INTO tb_user(name, attribute, contact)
VALUES ('Maria Oliveira', 'PRIMEIRO', '469740478');
INSERT INTO tb_user(name, attribute, contact)
VALUES ('Carlos Andrade', 'SEGUNDO', '4698745871');
INSERT INTO tb_user(name, attribute, contact)
VALUES ('Fernanda Lima', 'TERCEIRO', '789547921');
INSERT INTO tb_user(name, attribute, contact)
VALUES ('Roberto Souza', 'QUARTO', '47963578124');

CREATE TABLE tb_book (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         title VARCHAR(100) NOT NULL,
                         author VARCHAR(100) NOT NULL,
                         code VARCHAR(20) NOT NULL,
                         pages INT,
                         publisher VARCHAR(100),
                         edition INT NOT NULL DEFAULT 1,
                         active_loan BOOLEAN NOT NULL DEFAULT FALSE,

                         CONSTRAINT pk_book PRIMARY KEY (id),
                         CONSTRAINT uk_book_code UNIQUE (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO tb_book (title, author, code, pages, publisher, edition, active_loan)
VALUES
    ('Clean Code', 'Robert C. Martin', 'CC001', 464, 'Prentice Hall', 1, FALSE),
    ('Effective Java', 'Joshua Bloch', 'EJ002', 416, 'Addison-Wesley', 3, FALSE),
    ('Domain-Driven Design', 'Eric Evans', 'DDD003', 560, 'Addison-Wesley', 1, FALSE),
    ('Refactoring', 'Martin Fowler', 'RF004', 448, 'Addison-Wesley', 2, FALSE),
    ('Spring in Action', 'Craig Walls', 'SA005', 520, 'Manning', 5, FALSE);