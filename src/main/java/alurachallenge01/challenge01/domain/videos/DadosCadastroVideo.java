package alurachallenge01.challenge01.domain.videos;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroVideo(
		
		@NotBlank
		String titulo,
		
		@NotBlank
		String descricao,
		
		@NotBlank
		String url,
		
		Long id_categoria
		) {

}
