import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MostAgreeableStrategy implements I3VoteStrategy {
	public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
		String candidateWithMostVotes = null;
		int mostVotes = 0;

		for (Map.Entry<String, Votes> entry : votes.entrySet()) {
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
