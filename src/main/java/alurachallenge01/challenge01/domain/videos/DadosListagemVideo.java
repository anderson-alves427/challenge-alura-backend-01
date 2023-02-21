package alurachallenge01.challenge01.domain.videos;

public record DadosListagemVideo(Long id, String titulo, String descricao, String url, Long id_categoria) {
	public DadosListagemVideo(Video video) {
		this(video.getId(), video.getTitulo(), video.getDescricao(), video.getUrl(), video.getCategoria().getId());
	}
}
