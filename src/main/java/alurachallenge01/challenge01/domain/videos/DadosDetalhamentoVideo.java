package alurachallenge01.challenge01.domain.videos;

public record DadosDetalhamentoVideo(Long id, String titulo, String descricao, String url, Long id_categoria) {
    public DadosDetalhamentoVideo (Video video) {
        this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl(), video.getCategoria().getId());

    }
}

