import java.util.Optional;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class Examples {
	ElectionData eData = new ElectionData(new MostFirstVotesStrategy());

	@Test
	public void testOneVote() {
		try {
			eData.nominateCandidate("gompei");
			eData.nominateCandidate("husky");
			eData.nominateCandidate("bristaco");
			eData.submitVote("gompei", "husky", "bristaco");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals(Optional.of("gompei"), eData.calculateWinner());
	}

	@Test
	public void testTie() {
		try {
			eData.nominateCandidate("gompei");
			eData.nominateCandidate("husky");
			eData.nominateCandidate("bristaco");
			eData.submitVote("gompei", "husky", "bristaco");
			eData.submitVote("husky", "gompei", "bristaco");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals(Optional.empty(), eData.calculateWinner());
	}

	@Test
	public void testStrategyChange() {
		try {
			eData.nominateCandidate("gompei");
			eData.nominateCandidate("husky");
			eData.nominateCandidate("bristaco");
			eData.submitVote("gompei", "husky", "bristaco");
			eData.submitVote("bristaco", "husky", "gompei");
			eData.setStrategy(new MostAgreeableStrategy());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals(Optional.of("husky"), eData.calculateWinner());
	}

	@Test
	public void testGetCandidates() {
		try {
			eData.nominateCandidate("gompei");
			eData.nominateCandidate("husky");
			eData.nominateCandidate("bristaco");
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertEquals(3, eData.getCandidates().size());

		eData.getCandidates().add("newCandidate");

		assertEquals(3, eData.getCandidates().size());
	}

	@Test(expected = AlreadyNominatedException.class)
	public void testCandidateAlreadyNominated() throws AlreadyNominatedException {
		eData.nominateCandidate("gompei");
		eData.nominateCandidate("gompei");

		fail("did not throw exception properly");
	}

	@Test(expected = CandidateNotNominatedException.class)
	public void testCandidateNotNominated()
			throws CandidateNotNominatedException, MoreThanOnceException {
		eData.submitVote("gompei", "husky", "bristaco");

		fail("Did not throw exception properly");
	}

	@Test(expected = MoreThanOnceException.class)
	public void testCandidateMoreThanOnce()
			throws AlreadyNominatedException, CandidateNotNominatedException, MoreThanOnceException {
		eData.nominateCandidate("gompei");
		eData.submitVote("gompei", "gompei", "gompei");

		fail("Did not throw exception properly");
	}
}
