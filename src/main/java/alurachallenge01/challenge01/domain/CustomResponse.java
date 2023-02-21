package alurachallenge01.challenge01.domain;

public record CustomResponse(String message) {
	public CustomResponse(String message) {
		this.message = message;
	}
}
