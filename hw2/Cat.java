public class Cat extends Pet {
	/**
	 * whether or not the cat has been pet today
	 */
	public boolean hasBeenPetToday;

	/**
	 * The things a cat needs to provide, generically
	 *
	 * @param name
	 * @param age
	 * @param weightInOz
	 * @param location
	 * @param hasBeenPetToday
	 */
	public Cat(String name, int age, int weightInOz, Coord2D location, boolean hasBeenPetToday) {
		super(name, age, weightInOz, location);
		this.hasBeenPetToday = hasBeenPetToday;
	}

	/**
	 * how much of a particular food a cat will eat
	 *
	 * @param foodLabel the type of food being asked
	 * @return a number (0 if the cat does not eat that food)
	 */
	public int eats(String foodLabel) {
		switch (foodLabel) {
			case "cans":
				return hasBeenPetToday ? 2 : 1;
			case "treats":
				return 1 + (weightInOz / 8);
			default:
				return 0;
		}
	}
}
