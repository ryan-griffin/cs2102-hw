import java.util.HashMap;
import java.util.Optional;

public class MostAgreeableStrategy implements I3VoteStrategy {
	/**
	 * Calculates the winner of the election based on the total votes each candidate
	 * received.
	 *
	 * @param votes A map from candidate names to their Votes objects.
	 * @return An Optional containing the name of the candidate with the most total
	 *         votes,
	 *         or an empty Optional if there is no candidate or if there is a tie.
	 */
	public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
		String candidateWithMostVotes = null;
		int mostVotes = 0;

		for (HashMap.Entry<String, Votes> entry : votes.entrySet()) {
			int firstVotes = entry.getValue().getFirstVotes();
			int secondVotes = entry.getValue().getSecondVotes();
			int thirdVotes = entry.getValue().getThirdVotes();
			int totalVotes = firstVotes + secondVotes + thirdVotes;

			if (totalVotes > mostVotes) {
				mostVotes = totalVotes;
				candidateWithMostVotes = entry.getKey();
			}
		}

		return candidateWithMostVotes != null
				? Optional.of(candidateWithMostVotes)
				: Optional.empty();
	}
}
