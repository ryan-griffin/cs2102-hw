import java.util.LinkedList;

public class ChinchillaZone extends Zone {
	/**
	 * the chinchillas in the zone
	 * the food in the pantry
	 */
	public LinkedList<Chinchilla> chinchillas;
	public String pantryLabel = "Chinchilla: 0 pellets, 0 hay";

	/**
	 * @param chinchillas
	 */
	public ChinchillaZone(LinkedList<Chinchilla> chinchillas) {
		this.chinchillas = chinchillas;
	}

	/**
	 * @return the chinchillas in the zone
	 */
	public LinkedList<? extends Pet> getPets() {
		return chinchillas;
	}

	/**
	 * Returns the stock of the pantry for the zone
	 *
	 * @return a string printing out the pantry in the format of "Chinchilla: #
	 *         pellets, # hay"
	 */
	public String getPantryLabel() {
		return pantryLabel;
	}

	/**
	 * Looks up a chinchilla in the zone and computers its age relative to human
	 * years
	 *
	 * @param petName the name of the chinchilla.
	 * @return the age of the chinchilla converted into human years using the
	 *         per-chinchilla
	 *         calculation
	 *         or -1 if that chinchilla name is not in this zone
	 */
	public int inHumanYears(String petName) {
		for (Chinchilla chinchilla : chinchillas) {
			if (chinchilla.getName() == petName) {
				return chinchilla.getAge() * 10;
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
			case "pellets":
				String[] split = pantryLabel.split(", ");
				int numPellets = Integer.parseInt(split[0].split(" ")[1]);
				pantryLabel = "Chinchilla: " + (numPellets + foodAmt) + " pellets, " + split[1];
			case "hay":
				split = pantryLabel.split(", ");
				int numHay = Integer.parseInt(split[1].split(" ")[0]);
				pantryLabel = "Chinchilla: " + split[0] + ", " + (numHay + foodAmt) + " hay";
		}
		return this;
	}

	/**
	 * For each chinchilla in the zone, brings them in to eat
	 * Use their eats() method on each type of food in the pantry for them
	 *
	 * @return `this` zone for the purpose of method chaining
	 *         If a food item in the zone goes negative, set it to 0. (No need for
	 *         "unknown" logic).
	 */
	public Zone feedZone() {
		for (Chinchilla chinchilla : chinchillas) {
			String[] split = pantryLabel.split(", ");

			int realPelletAmt = Integer.parseInt(split[0].split(" ")[1]) - chinchilla.eats("pellets");
			int pelletAmt = realPelletAmt < 0 ? 0 : realPelletAmt;

			int realHayAmt = Integer.parseInt(split[1].split(" ")[0]) - chinchilla.eats("hay");
			int hayAmt = realHayAmt < 0 ? 0 : realHayAmt;

			pantryLabel = "Chinchilla: " + pelletAmt + " pellets, " + hayAmt + " hay";
		}
		return this;
	}
}
