CREATE TABLE produtos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    estoque INT NOT NULL,
    codigo_produto VARCHAR(100) NOT NULL UNIQUE,
    ativo BOOLEAN DEFAULT true,

    PRIMARY KEY (id)
);
