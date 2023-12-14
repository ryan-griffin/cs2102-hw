public class MoreThanOnceException extends Exception {
	public MoreThanOnceException(String candidate) {
		super(String.format("Candidate %s was nominated more than once", candidate));
	}
}
