public class Bird extends Pet {
	/**
	 * whether or not the bird's wings are clipped
	 */
	public boolean wingsClipped;

	/**
	 * The things a bird needs to provide, generically
	 *
	 * @param name
	 * @param age
	 * @param weightInOz
	 * @param location
	 * @param wingsClipped
	 */
	public Bird(String name, int age, int weightInOz, Coord2D location, boolean wingsClipped) {
		super(name, age, weightInOz, location);
		this.wingsClipped = wingsClipped;
	}

	/**
	 * how much of a particular food a bird will eat
	 *
	 * @param foodLabel the type of food being asked
	 * @return a number (0 if the bird does not eat that food)
	 */
	public int eats(String foodLabel) {
		return foodLabel == "seeds" ? 1 : 0;
	}
}
