create schema biblioteca;

CREATE TABLE IF NOT EXISTS tb_user (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    attribute VARCHAR(20) NOT NULL,
    active_loan BOOLEAN NOT NULL DEFAULT FALSE,
    contact VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

INSERT INTO tb_user(name, attribute,contact) VALUES('Juvenal Cardoso','SEXTO','469874182329');
INSERT INTO tb_user(name, attribute,contact) VALUES ('Maria Oliveira', 'PRIMEIRO','469740478');
INSERT INTO tb_user(name, attribute,contact) VALUES ('Carlos Andrade', 'SEGUNDO','4698745871');
INSERT INTO tb_user(name, attribute,contact) VALUES ('Fernanda Lima', 'TERCEIRO','789547921');
INSERT INTO tb_user(name, attribute,contact) VALUES ('Roberto Souza', 'QUARTO','47963578124');
