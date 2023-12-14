public class AlreadyNominatedException extends Exception {
	/**
	 * Constructs a new AlreadyNominatedException with a detailed message.
	 *
	 * @param candidate The name of the candidate who was already nominated.
	 */
	public AlreadyNominatedException(String candidate) {
		super(String.format("Candidate %s was already nominated", candidate));
	}
}
