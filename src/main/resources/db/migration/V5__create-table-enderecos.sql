CREATE TABLE enderecos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome_rua VARCHAR(100) NOT NULL,
    numero_casa INT NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL, -- UF armazenado como string de 2 caracteres
    cliente_id BIGINT NOT NULL,


    PRIMARY KEY (id)
);
