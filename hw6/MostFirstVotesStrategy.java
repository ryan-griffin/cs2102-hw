import java.util.HashMap;
import java.util.Optional;

public class MostFirstVotesStrategy implements I3VoteStrategy {
	/**
	 * Calculates the winner of the election based on the first votes each candidate
	 * received.
	 * The candidate with the most first votes wins, but only if they have more than
	 * half of the total first votes.
	 *
	 * @param votes A map from candidate names to their Votes objects.
	 * @return An Optional containing the name of the candidate with the most first
	 *         votes and more than half of the total first votes,
	 *         or an empty Optional if there is no such candidate or if there is a
	 *         tie.
	 */
	public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
		String candidateWithMostFirstVotes = null;
		int mostFirstVotes = 0;
		int totalFirstVotes = 0;

		for (HashMap.Entry<String, Votes> entry : votes.entrySet()) {
			int firstVotes = entry.getValue().getFirstVotes();
			totalFirstVotes += firstVotes;

			if (firstVotes > mostFirstVotes) {
				mostFirstVotes = firstVotes;
				candidateWithMostFirstVotes = entry.getKey();
			}
		}

		return candidateWithMostFirstVotes != null && mostFirstVotes > totalFirstVotes / 2
				? Optional.of(candidateWithMostFirstVotes)
				: Optional.empty();
	}
}
