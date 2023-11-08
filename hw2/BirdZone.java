import java.util.LinkedList;

public class BirdZone extends Zone {

	/**
	 * the birds in the zone
	 * the food in the pantry
	 */
	public LinkedList<Bird> birds;
	public String pantryLabel = "Bird: 0 seeds";

	/**
	 * @param birds
	 */
	public BirdZone(LinkedList<Bird> birds) {
		this.birds = birds;
	}

	/**
	 * @return the birds in the zone
	 */
	public LinkedList<? extends Pet> getPets() {
		return birds;
	}

	/**
	 * Returns the stock of the pantry for the bird zone
	 *
	 * @return a string printing out the pantry in the format of "Bird: # seeds"
	 */
	public String getPantryLabel() {
		return pantryLabel;
	}

	/**
	 * Looks up a bird in the zone and computers its age relative to human years
	 *
	 * @param petName the name of the bird.
	 * @return the age of the bird converted into human years
	 *         or -1 if that bird name is not in this zone
	 */
	public int inHumanYears(String petName) {
		for (Bird bird : birds) {
			if (bird.getName() == petName) {
				return bird.getAge() * 9;
			}
		}
		return -1;
	}

	/**
	 * add to the current pantry's amount of food when this method is called
	 *
	 * @param foodName The name of a food item expected to be stocked in this zone's
	 *                 pantry
	 * @param foodAmt  A non-negative number (>= 0) representing how much of that
	 *                 food is going into the pantry
	 * @return `this` zone for the purpose of method chaining
	 */
	public Zone restockPetFood(String foodName, int foodAmt) {
		if (foodName == "seeds") {
			String[] split = pantryLabel.split(" ");
			int numSeeds = Integer.parseInt(split[1]);
			pantryLabel = "Bird: " + (numSeeds + foodAmt) + " seeds";

		}
		return this;
	}

	/**
	 * For each bird in the zone, brings them in to eat
	 * Use their eats() method on each type of food in the pantry for them
	 *
	 * @return `this` zone for the purpose of method chaining
	 *         If a food item in the zone goes negative, set it to 0. (No need for
	 *         "unknown" logic).
	 */
	public Zone feedZone() {
		for (Bird bird : birds) {
			String[] split = pantryLabel.split(" ");

			int realSeedAmt = Integer.parseInt(split[1]) - bird.eats("seeds");
			int seedAmt = realSeedAmt < 0 ? 0 : realSeedAmt;

			pantryLabel = "Bird: " + seedAmt + " seeds";
		}
		return this;
	}
}
