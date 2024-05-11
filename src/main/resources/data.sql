-- Inserir na tabela Product
INSERT INTO product (uuid, product_id, name, description, price, category)
VALUES (5420358, 4320794, 'Sundae de chocolate', 'Com cobertura deliciosa!!!', 3.5, 3);

-- Inserir na tabela Order
INSERT INTO kitchen_order (id, customer_id, kitchen_order_status, last_update)
VALUES (8678007, 983615586, 0, '2024-05-07 21:18:10 ');

-- Inserir na tabela ProductQuantity
INSERT INTO product_quantity (id, product_id, quantity, order_id)
VALUES (6787408, 5420358, 2, 8678007);
