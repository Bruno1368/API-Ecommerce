CREATE TABLE itens_venda (
    id BIGINT NOT NULL AUTO_INCREMENT,
    venda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INT NOT NULL,
    
    PRIMARY KEY (id),
    
    -- Definindo as chaves estrangeiras
    FOREIGN KEY (venda_id) REFERENCES vendas(id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);
