import org.junit.Test;
import static org.junit.Assert.*;
import java.util.LinkedList;

public class Examples {
	LinkedList<Integer> someBirdWeights = new LinkedList<Integer>();
	LinkedList<Integer> someDogYears = new LinkedList<Integer>();
	String somePetOfTheMonth = new String();
	LinkedList<Coord> someCatCoords = new LinkedList<Coord>();
	PetRescue pr = new PetRescue(someBirdWeights, someDogYears, somePetOfTheMonth, someCatCoords);

	public Examples() {
		someBirdWeights.add(10); // 10oz
	}

	@Test
	public void testDogYearsNonEmpty() {
		someDogYears.add(2);
		someDogYears.add(3);

		LinkedList<Integer> someHumanYears = new LinkedList<>();
		someHumanYears.add(14);
		someHumanYears.add(21);
		assertEquals(someHumanYears, pr.inHumanYears());
	}

	@Test
	public void testBirdWeightsNonEmpty() {
		assertEquals(10, pr.biggestBird()); // label text (optional),
											// expected value,
											// actual/check value
	}

	@Test
	public void testBestowHonor() {
		pr.bestowHonor("Dr.", "PhD");
		assertEquals("Dr. , PhD", pr.petOfTheMonth);
	}

	@Test
	public void testBestowHonor2() {
		pr.bestowHonor("Dr.", "PhD");
		pr.bestowHonor("Capt.", "Esq.");
		assertEquals("Capt. Dr. , PhD Esq.", pr.petOfTheMonth);
	}

	@Test
	public void testFeedChinchillasZero() {
		assertEquals("Chinchilla: 0 pellets, 0 hay", pr.feedChinchillas(0, 0));
	}

	@Test
	public void testFeedChinchillasPositive() {
		assertEquals("Chinchilla: 5 pellets, 10 hay", pr.feedChinchillas(5, 10));
	}

	@Test
	public void testFeedChinchillasNegative() {
		assertEquals("Chinchilla: unknown pellets, unknown hay", pr.feedChinchillas(-5, -10));
	}

	@Test
	public void testClosestToEmpty() {
		assertEquals("Conspiciously Catless", pr.closestTo(0, 0));
	}

	@Test
	public void testClosestToNonEmpty() {
		someCatCoords.add(new Coord("Cat1", 0, 0));
		someCatCoords.add(new Coord("Cat2", 1, 1));
		someCatCoords.add(new Coord("Cat3", 2, 2));
		assertEquals("Cat1", pr.closestTo(0, 0));
	}
}
