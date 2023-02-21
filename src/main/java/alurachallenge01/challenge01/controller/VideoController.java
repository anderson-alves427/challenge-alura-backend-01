package alurachallenge01.challenge01.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import alurachallenge01.challenge01.domain.videos.DadosDetalhamentoVideo;
import alurachallenge01.challenge01.domain.videos.DadosListagemVideo;
import alurachallenge01.challenge01.domain.CustomResponse;
import alurachallenge01.challenge01.domain.videos.DadosAtualizacaoVideo;
import alurachallenge01.challenge01.domain.videos.DadosCadastroVideo;
import alurachallenge01.challenge01.domain.videos.Video;
import alurachallenge01.challenge01.domain.videos.VideoRepository;
import alurachallenge01.challenge01.domain.videos.VideoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("videos")
public class VideoController {

	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private VideoService videoService;
	
	@PostMapping()
	@Transactional
	public ResponseEntity<DadosDetalhamentoVideo> cadastrar(@RequestBody @Valid DadosCadastroVideo dadosVideo,  UriComponentsBuilder uriBuilder) {
		
		DadosDetalhamentoVideo video = videoService.cadastraVideo(dadosVideo);
		URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.id()).toUri();
		
		return ResponseEntity.created(uri).body(video);
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemVideo>> listagem(@RequestParam("search") String search, Pageable paginacao) {
		Page<DadosListagemVideo> page = videoService.listaVideos(search, paginacao);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoVideo> detalhar(@PathVariable Long id) {
		Video video = videoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoVideo(video));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoVideo> atualizar(@RequestBody @Valid DadosAtualizacaoVideo dadosVideo) {
		DadosDetalhamentoVideo video = videoService.atualizaInformacoes(dadosVideo);
		
		return ResponseEntity.ok(video);
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<CustomResponse> deletar(@PathVariable Long id) {
		Video video = videoRepository.getReferenceById(id);
		video.deletarInformacoes();
		CustomResponse response = new CustomResponse("Recurso excluído com sucesso");
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("categorias/{id}/videos")
	public ResponseEntity<DadosDetalhamentoVideo> buscaPorCategoria(@PathVariable Long id) {
		DadosDetalhamentoVideo video = videoService.buscaVideoPorCategoria(id);
		return ResponseEntity.ok(video);
	}
	
}
