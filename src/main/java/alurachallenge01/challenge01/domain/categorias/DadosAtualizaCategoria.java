package alurachallenge01.challenge01.domain.categorias;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaCategoria(
		@NotNull
		Long id,
		
		String titulo,
		
		String cor
		) {

}
