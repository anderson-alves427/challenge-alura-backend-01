ALTER TABLE categorias ADD COLUMN ativo TINYINT;
UPDATE categorias SET ativo = 1;