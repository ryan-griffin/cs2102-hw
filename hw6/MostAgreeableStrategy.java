import java.util.HashMap;
import java.util.Optional;

public class MostAgreeableStrategy implements I3VoteStrategy {
	public Optional<String> calculateWinner(HashMap<String, Votes> votes) {
		return Optional.empty();
	}
}
