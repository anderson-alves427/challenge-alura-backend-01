package alurachallenge01.challenge01.domain.videos;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoVideo(
        @NotNull
        Long id, 
        String titulo,
        String descricao,
        String url,
        Long id_categoria
		) {
}