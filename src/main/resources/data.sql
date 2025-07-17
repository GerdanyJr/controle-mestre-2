-- CATEGORIAS
INSERT INTO categoria (id, nome)
VALUES (1, 'Bebidas');
INSERT INTO categoria (id, nome)
VALUES (2, 'Alimentos');
INSERT INTO categoria (id, nome)
VALUES (3, 'Limpeza');

-- PRODUTOS
INSERT INTO produto (nome, ean, quantidade_minima, marca, categoria_id, quantidade, valor)
VALUES ('Refrigerante Cola', '12345678950', 10, 'Coca-Cola', 1, 100, 5.99),
       ('Arroz Branco 5kg', '12345678951', 20, 'Tio João', 2, 50, 22.50),
       ('Sabão em Pó 1kg', '12345678954', 5, 'Omo', 3, 30, 9.99);

-- CLIENTES
INSERT INTO cliente (cpf, nome, sexo, data_nascimento)
VALUES ('12345678901', 'Maria Silva', 1, '1990-04-15'),
       ('98765432100', 'João Santos', 2, '1985-09-22');

-- FUNCIONÁRIOS
INSERT INTO funcionario (cpf, email, telefone, nome, apelido, cargo)
VALUES ('11122233344', 'maria.vendas@empresa.com', '71999998888', 'Maria Vendedora', 'Mari', 1),
       ('55566677788', 'joao.vendas@empresa.com', '71988887777', 'João Vendedor', 'Jão', 2);

-- VENDAS
INSERT INTO venda (data, cliente_id, funcionario_id, total)
VALUES ('2025-07-16', 1, 1, 39.48);

-- PRODUTOS VENDIDOS
INSERT INTO produto_venda (venda_id, produto_id, valor_unitario, quantidade)
VALUES (1, 1, 5.99, 2),
       (1, 2, 22.50, 1),
       (1, 3, 9.99, 1);
