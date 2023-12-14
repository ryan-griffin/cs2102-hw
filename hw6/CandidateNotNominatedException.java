public class CandidateNotNominatedException extends Exception {
	private String candidateName;

	/**
	 * Constructs a new CandidateNotNominatedException with a detailed message.
	 *
	 * @param candidate The name of the candidate who was not nominated.
	 */
	public CandidateNotNominatedException(String candidate) {
		super(String.format("Candidate %s was not nominated", candidate));
		candidate = candidateName;
	}

	/**
	 * Returns the name of the candidate who was not nominated.
	 *
	 * @return The name of the candidate.
	 */
	public String getCandidate() {
		return candidateName;
	}
}
