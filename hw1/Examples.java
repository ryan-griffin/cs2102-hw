import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedList;

public class Examples {

	PetRescue pr;
	LinkedList<Integer> someBirdWeights = new LinkedList<Integer>();

	public Examples() {
		someBirdWeights.add(10); // 10oz
	}

	@Test
	public void testDogYearsNonEmpty() {
		LinkedList<Integer> birdWeight = new LinkedList<>();
		LinkedList<Integer> dogYears = new LinkedList<>();
		LinkedList<Coord> catCords = new LinkedList<>();
		dogYears.add(2);
		dogYears.add(3);
		pr = new PetRescue(birdWeight, dogYears, "", catCords);

		LinkedList<Integer> someHumanYears = new LinkedList<>();
		someHumanYears.add(14);
		someHumanYears.add(21);
		assertEquals("Dog year in human years should be 14 and 21 respectively.", someHumanYears, pr.inHumanYears());
	}

	@Test
	public void testBirdWeightsNonEmpty() {
		pr = new PetRescue(someBirdWeights, new LinkedList<Integer>(), "", new LinkedList<Coord>());
		assertEquals("10 should be the biggest bird out of 1 bird", 10, pr.biggestBird()); // label text (optional),
																							// expected value,
																							// actual/check value
	}

	@Test
	public void testBestowHonor() {
		pr = new PetRescue(new LinkedList<Integer>(), new LinkedList<Integer>(), "", new LinkedList<Coord>());
		pr.bestowHonor("Dr.", "PhD");
		assertEquals("Name should be 'Dr. , PhD'", "Dr. , PhD", pr.petOfTheMonth);
	}
}
