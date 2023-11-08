import java.util.LinkedList;

public class CatZone extends Zone {
	/**
	 * the cats in the zone
	 * the food in the pantry
	 */
	public LinkedList<Cat> cats;
	public String pantryLabel = "Cat: 0 cans, 0 treats";

	/**
	 * @param cats
	 */
	public CatZone(LinkedList<Cat> cats) {
		this.cats = cats;
	}

	/**
	 * @return the cats in the zone
	 */
	public LinkedList<? extends Pet> getPets() {
		return cats;
	}

	/**
	 * Returns the stock of the pantry for the cat zone
	 *
	 * @return a string printing out the pantry in the format of "Cat: # cans, #
	 *         treats"
	 */
	public String getPantryLabel() {
		return pantryLabel;
	}

	/**
	 * Looks up a cat in the zone and computers its age relative to human years
	 *
	 * @param petName the name of the cat.
	 * @return the age of the cat converted into human years
	 *         or -1 if that cat name is not in this zone
	 */
	public int inHumanYears(String petName) {
		for (Cat cat : cats) {
			if (cat.getName() == petName) {
				return cat.getAge() * 6;
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
		switch (foodName) {
			case "cans":
				String[] split = pantryLabel.split(" ");
				int numCans = Integer.parseInt(split[1]);
				pantryLabel = "Cat: " + (numCans + foodAmt) + " cans, " + split[3] + " treats";
			case "treats":
				split = pantryLabel.split(" ");
				int numTreats = Integer.parseInt(split[3]);
				pantryLabel = "Cat: " + split[1] + " cans, " + (numTreats + foodAmt) + " treats";
		}
		return this;
	}

	/**
	 * For each animal in the zone, brings them in to eat
	 * Use their eats() method on each type of food in the pantry for them
	 *
	 * @return `this` zone for the purpose of method chaining
	 *         If a food item in the zone goes negative, set it to 0. (No need for
	 *         "unknown" logic).
	 */
	public Zone feedZone() {
		for (Cat cat : cats) {
			String[] split = pantryLabel.split(" ");

			int realCanAmt = Integer.parseInt(split[1]) - cat.eats("cans");
			int canAmt = realCanAmt < 0 ? 0 : realCanAmt;

			int realTreatAmt = Integer.parseInt(split[3]) - cat.eats("treats");
			int treatAmt = realTreatAmt < 0 ? 0 : realTreatAmt;

			pantryLabel = "Cat: " + canAmt + " cans, " + treatAmt + " treats";
		}
		return this;
	}
}
