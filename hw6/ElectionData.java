import java.util.*;

public class ElectionData {
	private I3VoteStrategy strategy;
	private Map<String, Votes> candidates;

	public ElectionData(I3VoteStrategy strategy) {
		this.strategy = strategy;
		this.candidates = new HashMap<>();
	}

	public void setStrategy(I3VoteStrategy strategy) {
		this.strategy = strategy;
	}

	public Set<String> getCandidates() {
		return new HashSet<>(candidates.keySet());
	}

	public void nominateCandidate(String person) throws AlreadyNominatedException {
		if (candidates.containsKey(person)) {
			throw new AlreadyNominatedException("This candidate is already nominated");
		}
		candidates.put(person, new Votes(0, 0, 0));
	}

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

	public Optional<String> calculateWinner() {
		return strategy.calculateWinner(new HashMap<>(candidates));
	}
}
