package alurachallenge01.challenge01.domain.categorias;

import java.util.List;

import alurachallenge01.challenge01.domain.videos.Video;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name="categorias")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String cor;
	
	private Boolean ativo;
	
	@OneToMany(mappedBy = "categoria")
    private List<Video> videos;
	
	public Categoria(DadosCadastroCategoria dadosCategoria) {
		this.titulo = dadosCategoria.titulo();
		this.cor = dadosCategoria.cor();
		this.ativo = true;
	}
	
}
