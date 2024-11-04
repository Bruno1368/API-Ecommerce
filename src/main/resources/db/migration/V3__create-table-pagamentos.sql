CREATE TABLE pagamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    status VARCHAR(20) NOT NULL,
    metodo_pagamento VARCHAR(20) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,

    PRIMARY KEY(id)
);