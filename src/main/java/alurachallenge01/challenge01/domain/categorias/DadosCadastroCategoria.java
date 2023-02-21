package alurachallenge01.challenge01.domain.categorias;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCategoria(
		
		@NotBlank
		String titulo,
		
		@NotBlank
		String cor
		) {

}
