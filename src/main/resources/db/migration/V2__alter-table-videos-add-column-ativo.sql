ALTER TABLE videos ADD COLUMN ativo TINYINT;
UPDATE videos SET ativo = 1;