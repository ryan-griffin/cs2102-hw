public class MoreThanOnceException extends Exception {
	/**
	 * Constructs a new MoreThanOnceException with a detailed message.
	 *
	 * @param candidate The name of the candidate who was nominated more than once.
	 */
	public MoreThanOnceException(String candidate) {
		super(String.format("Candidate %s was nominated more than once", candidate));
	}
}
