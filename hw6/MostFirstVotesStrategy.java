import java.util.HashMap;
import java.util.Optional;

public class MostFirstVotesStrategy implements I3VoteStrategy {
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
