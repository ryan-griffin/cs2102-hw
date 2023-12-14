public class AlreadyNominatedException extends Exception {
	public AlreadyNominatedException(String candidate) {
		super(String.format("Candidate %s was already nominated", candidate));
	}
}
