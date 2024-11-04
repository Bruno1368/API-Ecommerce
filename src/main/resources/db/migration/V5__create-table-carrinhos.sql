CREATE TABLE carrinhos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    cliente_id BIGINT,

    PRIMARY KEY(id),
    FOREIGN KEY(cliente_id) REFERENCES clientes(id)

);