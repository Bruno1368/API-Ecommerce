CREATE TABLE clientes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    endereco_id BIGINT, -- Chave estrangeira opcional que referencia o endereço

    PRIMARY KEY (id)
     -- Relaciona com a tabela de endereços
);
