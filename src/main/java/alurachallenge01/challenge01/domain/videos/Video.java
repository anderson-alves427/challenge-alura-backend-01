package alurachallenge01.challenge01.domain.videos;

import alurachallenge01.challenge01.domain.categorias.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
@Table(name = "videos")
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String descricao;
	
	private String url;
	
	private Boolean ativo;
	
	@ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
	
	public Video(DadosCadastroVideo dadosVideo, Categoria dadosCategoria) {
		this.titulo = dadosVideo.titulo();
		this.descricao = dadosVideo.descricao();
		this.url = dadosVideo.url();
		this.ativo = true;
		this.categoria = dadosCategoria;
	}

	public void deletarInformacoes() {
		this.ativo = false;
	}

}
