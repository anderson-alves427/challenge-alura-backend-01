package alurachallenge01.challenge01.domain.videos;

public record CustomResponse(String message) {
	public CustomResponse(String message) {
		this.message = message;
	}
}
