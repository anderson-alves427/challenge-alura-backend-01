package alurachallenge01.challenge01.domain.categorias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alurachallenge01.challenge01.domain.CustomResponse;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public DadosListagemCategoria atualizaInformacoes(DadosAtualizaCategoria dadosCategoria) {
		Categoria categoria = categoriaRepository.getReferenceById(dadosCategoria.id());
		
		if (dadosCategoria.titulo() != null) {
			categoria.setTitulo(dadosCategoria.titulo());
		}
		
		if (dadosCategoria.cor() != null) {
			categoria.setCor(dadosCategoria.cor());
		}
		
		
		return new DadosListagemCategoria(categoria);
	}

	public CustomResponse deletaCategoria(Long id) {
		Categoria categoria = categoriaRepository.getReferenceById(id);
		
		categoria.setAtivo(false);
		
		CustomResponse resposta = new CustomResponse("Registro deletado com sucesso.");
		
		return resposta;
	}
	
	
}
