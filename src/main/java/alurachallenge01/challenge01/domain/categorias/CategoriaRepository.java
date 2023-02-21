package alurachallenge01.challenge01.domain.categorias;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	Page<Categoria> findAllByAtivoTrue(Pageable paginacao);
}
