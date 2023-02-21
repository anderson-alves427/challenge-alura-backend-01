package alurachallenge01.challenge01.domain.videos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import alurachallenge01.challenge01.domain.categorias.Categoria;

public interface VideoRepository extends JpaRepository<Video, Long>{

	Page<Video> findAllByAtivoTrue(Pageable paginacao);

	Video getReferenceByCategoria(Categoria categoria);
	
	Page<Video> findByTituloLikeAndAtivoTrue(String search, Pageable paginacao);

	boolean existsByCategoria(Categoria categoria);

}
