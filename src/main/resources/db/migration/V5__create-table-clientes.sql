CREATE TABLE clientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    endereco_id BIGINT, -- Chave estrangeira para endereços

    PRIMARY KEY (id),
    FOREIGN KEY (endereco_id) REFERENCES enderecos(id)
);
