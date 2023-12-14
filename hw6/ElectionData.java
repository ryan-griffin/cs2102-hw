import java.util.*;

public class ElectionData {
	private I3VoteStrategy strategy;
	private Map<String, Votes> candidates;

	/**
	 * Constructor for the ElectionData class.
	 *
	 * @param strategy The voting strategy to be used in the election.
	 */
	public ElectionData(I3VoteStrategy strategy) {
		this.strategy = strategy;
		this.candidates = new HashMap<>();
	}

	/**
	 * Sets the voting strategy for the election.
	 *
	 * @param strategy The new voting strategy.
	 */
	public void setStrategy(I3VoteStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Returns a set of all candidates in the election.
	 *
	 * @return A set of all candidates.
	 */
	public Set<String> getCandidates() {
		return new HashSet<>(candidates.keySet());
	}

	/**
	 * Nominates a new candidate for the election.
	 *
	 * @param person The name of the candidate to nominate.
	 * @throws AlreadyNominatedException If the candidate is already nominated.
	 */
	public void nominateCandidate(String person) throws AlreadyNominatedException {
		if (candidates.containsKey(person)) {
			throw new AlreadyNominatedException("This candidate is already nominated");
		}
		candidates.put(person, new Votes(0, 0, 0));
	}

	/**
	 * Submits a vote for three different candidates.
	 *
	 * @param first  The name of the first candidate.
	 * @param second The name of the second candidate.
	 * @param third  The name of the third candidate.
	 * @throws CandidateNotNominatedException If one of the candidates is not
	 *                                        nominated.
	 * @throws MoreThanOnceException          If the same candidate is voted for
	 *                                        more than once.
	 */
	public void submitVote(String first, String second, String third)
			throws CandidateNotNominatedException, MoreThanOnceException {
		if (!candidates.containsKey(first) || !candidates.containsKey(second) || !candidates.containsKey(third)) {
			throw new CandidateNotNominatedException("One of the candidates is not nominated");
		}
		if (first.equals(second) || second.equals(third) || first.equals(third)) {
			throw new MoreThanOnceException("You cannot vote for the same candidate more than once");
		}
		candidates.get(first).voteFirst();
		candidates.get(second).voteSecond();
		candidates.get(third).voteThird();
	}

	/**
	 * Calculates the winner of the election based on the current votes.
	 *
	 * @return An Optional containing the name of the winner, or an empty Optional
	 *         if there is no winner.
	 */
	public Optional<String> calculateWinner() {
		return strategy.calculateWinner(new HashMap<>(candidates));
	}
}
