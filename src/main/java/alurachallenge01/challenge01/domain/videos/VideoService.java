package alurachallenge01.challenge01.domain.videos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import alurachallenge01.challenge01.domain.ValidacaoException;
import alurachallenge01.challenge01.domain.categorias.Categoria;
import alurachallenge01.challenge01.domain.categorias.CategoriaRepository;

@Service
public class VideoService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private VideoRepository videoRepository;

	public DadosDetalhamentoVideo cadastraVideo(DadosCadastroVideo dadosVideo) {

		if ((dadosVideo.id_categoria() == null)) {
			if(!categoriaRepository.existsById(Long.valueOf (1))) {
				throw new ValidacaoException("Categoria Livre (padrão) não encontrada para o cadastro");
			}
			
			Categoria categoria = categoriaRepository.getReferenceById(Long.valueOf(1));
			
			Video video = new Video(dadosVideo, categoria);
			videoRepository.save(video);
			
			return new DadosDetalhamentoVideo(video);
			
		}
		
		if (!categoriaRepository.existsById(dadosVideo.id_categoria())) {
            throw new ValidacaoException("Categoria não existente");
        }
		
		Categoria categoria = categoriaRepository.getReferenceById(dadosVideo.id_categoria());
		
		Video video = new Video(dadosVideo, categoria);
		videoRepository.save(video);
		
		System.out.println(video.getCategoria().getId());
		
		return new DadosDetalhamentoVideo(video);
		
	}
	
	public DadosDetalhamentoVideo atualizaInformacoes(DadosAtualizacaoVideo dadosVideo) {
		if(!videoRepository.existsById(dadosVideo.id())) {
			throw new ValidacaoException("Video não existente.");
		}
		
		Video video = videoRepository.getReferenceById(dadosVideo.id());
		
		System.out.println(video);
		
		if ( dadosVideo.titulo() != null) {
			video.setTitulo(dadosVideo.titulo());
		}
		
		if ( dadosVideo.descricao() != null) {
			video.setDescricao(dadosVideo.descricao());
		}
		
		if ( dadosVideo.url() != null) {
			video.setUrl(dadosVideo.url());
		}
		
		if (dadosVideo.id_categoria() != null) {
			Categoria categoria = retornaReferenciaCategoria(dadosVideo.id_categoria());
			video.setCategoria(categoria);
		}
		
		
		return new DadosDetalhamentoVideo(video);
	}
	
	public Categoria retornaReferenciaCategoria(Long id) {
		if (!categoriaRepository.existsById(id)) {
            throw new ValidacaoException("Categoria não existente");
        }
		
		Categoria categoria = categoriaRepository.getReferenceById(id);
		
		return categoria;
	}
	
	public DadosDetalhamentoVideo buscaVideoPorCategoria(Long id) {
		Categoria categoria = categoriaRepository.getReferenceById(id);
		
		if (!videoRepository.existsByCategoria(categoria)) {
			throw new ValidacaoException("Video não existe");
		}
		Video video = videoRepository.getReferenceByCategoria(categoria);
		
		return new DadosDetalhamentoVideo(video);
		
	}

	public Page<DadosListagemVideo> listaVideos(String search, Pageable paginacao) {
		String pesquisa = "%" + search + "%";
		Page<DadosListagemVideo> lista = videoRepository.findByTituloLikeAndAtivoTrue(pesquisa, paginacao).map(DadosListagemVideo::new);
		return lista;
	}
	
}
