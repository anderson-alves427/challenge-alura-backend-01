package alurachallenge01.challenge01.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import alurachallenge01.challenge01.domain.categorias.DadosListagemCategoria;
import alurachallenge01.challenge01.domain.categorias.DadosCadastroCategoria;
import alurachallenge01.challenge01.domain.CustomResponse;
import alurachallenge01.challenge01.domain.categorias.Categoria;
import alurachallenge01.challenge01.domain.categorias.CategoriaRepository;
import alurachallenge01.challenge01.domain.categorias.CategoriaService;
import alurachallenge01.challenge01.domain.categorias.DadosAtualizaCategoria;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemCategoria>> listar(Pageable paginacao) {
		Page<DadosListagemCategoria> page = categoriaRepository.findAllByAtivoTrue(paginacao).map(DadosListagemCategoria::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DadosListagemCategoria> listaPorId(@PathVariable Long id) {
		Categoria categoria = categoriaRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosListagemCategoria(categoria));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosListagemCategoria> cadastrar(@RequestBody @Valid DadosCadastroCategoria dadosCategoria, UriComponentsBuilder uriBuilder) {
		Categoria categoria = new Categoria(dadosCategoria);
		categoriaRepository.save(categoria);
		
		URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosListagemCategoria(categoria));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosListagemCategoria> atualiza(@RequestBody @Valid DadosAtualizaCategoria dadosCategoria) {
		
		DadosListagemCategoria categoria = categoriaService.atualizaInformacoes(dadosCategoria);
		return ResponseEntity.ok(categoria);
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<CustomResponse> deleta(@PathVariable Long id) {
		CustomResponse mensagem = categoriaService.deletaCategoria(id);
		return ResponseEntity.ok(mensagem);
	}
}
