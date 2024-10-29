ALTER TABLE vendas
ADD COLUMN pagamento BIGINT,
ADD COLUMN cliente BIGINT,
ADD COLUMN valor_total DECIMAL(10, 2),
ADD CONSTRAINT fk_pagamento FOREIGN KEY (pagamento) REFERENCES pagamentos(id),
ADD CONSTRAINT fk_cliente FOREIGN KEY (cliente) REFERENCES clientes(id);
