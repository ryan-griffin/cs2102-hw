public class CandidateNotNominatedException extends Exception {
	private String candidateName;

	public CandidateNotNominatedException(String candidate) {
		super(String.format("Candidate %s was not nominated", candidate));
		candidate = candidateName;
	}

	public String getCandidate() {
		return candidateName;
	}
}
