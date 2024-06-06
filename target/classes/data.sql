-- Inserções para a tabela `Show`
INSERT INTO Show (nome, localizacao, data) VALUES ('Rock in Rio', 'Parque Olímpico, Rio de Janeiro', '2024-09-27T20:00:00');
INSERT INTO Show (nome, localizacao, data) VALUES ('Lollapalooza', 'Autódromo de Interlagos, São Paulo', '2024-03-25T14:00:00');
INSERT INTO Show (nome, localizacao, data) VALUES ('Tomorrowland Brasil', 'Itu, São Paulo', '2024-07-14T18:00:00');

-- Inserções para a tabela `Ingresso`
-- Assumindo que os IDs para os shows sejam 1, 2 e 3 respectivamente
INSERT INTO Ingresso (preco, tipo_ingresso, show_id) VALUES (590.00, 'INTEIRA', 1);
INSERT INTO Ingresso (preco, tipo_ingresso, show_id) VALUES (550.00, 'MEIA', 1);
INSERT INTO Ingresso (preco, tipo_ingresso, show_id) VALUES (80.00, 'CORTESIA', 1);

INSERT INTO Ingresso (preco, tipo_ingresso, show_id) VALUES (400.00, 'INTEIRA', 2);
INSERT INTO Ingresso (preco, tipo_ingresso, show_id) VALUES (200.00, 'MEIA', 2);
INSERT INTO Ingresso (preco, tipo_ingresso, show_id) VALUES (300.00, 'CORTESIA', 2);

INSERT INTO Ingresso (preco, tipo_ingresso, show_id) VALUES (1200.00, 'INTEIRA', 3);
INSERT INTO Ingresso (preco, tipo_ingresso, show_id) VALUES (600.00, 'MEIA', 3);
INSERT INTO Ingresso (preco, tipo_ingresso, show_id) VALUES (200.00, 'CORTESIA', 3);
