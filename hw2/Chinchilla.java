public class Chinchilla extends Pet {
	/**
	 * how much dust is on the chinchilla
	 */
	public int dustBathResidueInOz;

	/**
	 * The things a pet needs to provide, generically
	 *
	 * @param name
	 * @param age
	 * @param weightInOz
	 * @param location
	 * @param dustBathResidueInOz
	 */
	public Chinchilla(String name, int age, int weightInOz, Coord2D location, int dustBathResidueInOz) {
		super(name, age, weightInOz, location);
		this.dustBathResidueInOz = dustBathResidueInOz;
	}

	/**
	 * Produces the name of this pet in the rescue
	 *
	 * @return the pet's name as a string
	 */
	public int getWeightInOz() {
		return weightInOz + dustBathResidueInOz;
	}

	/**
	 * how much of a particular food a chinchilla will eat
	 *
	 * @param foodLabel the type of food being asked
	 * @return a number (0 if the chinchilla does not eat that food)
	 */
	public int eats(String foodLabel) {
		switch (foodLabel) {
			case "pellets":
				return 3;
			case "hay":
				return 1;
			default:
				return 0;
		}
	}
}
