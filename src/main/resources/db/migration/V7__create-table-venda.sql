CREATE TABLE vendas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    data DATE NOT NULL,
    carrinho_id BIGINT,
    pagamento_id BIGINT,
    cliente_id BIGINT,
    valor_total DECIMAL(10, 2),

    FOREIGN KEY (pagamento_id) REFERENCES pagamentos(id),
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (carrinho_id) REFERENCES carrinhos(id),

    PRIMARY KEY (id)
);
