ALTER TABLE videos
ADD CONSTRAINT fk_videos_categorias
FOREIGN KEY (id_categoria)
REFERENCES categorias (id);

UPDATE videos SET id_categoria = 1;
