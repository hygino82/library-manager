create schema biblioteca;

CREATE TABLE IF NOT EXISTS tb_user (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    attribute VARCHAR(20) NOT NULL,
    active_loan BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

insert into tb_user(name,attribute)  values('Juvenal Cardoso','SEXTO');
INSERT INTO tb_user(name, attribute) VALUES ('Maria Oliveira', 'PRIMEIRO');
INSERT INTO tb_user(name, attribute) VALUES ('Carlos Andrade', 'SEGUNDO');
INSERT INTO tb_user(name, attribute) VALUES ('Fernanda Lima', 'TERCEIRO');
INSERT INTO tb_user(name, attribute) VALUES ('Roberto Souza', 'QUARTO');
