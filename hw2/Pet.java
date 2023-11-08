public abstract class Pet implements Petable {
	/**
	 * name of the pet
	 * age of the pet
	 * weight of the pet in oz
	 * location of the pet
	 */
	public String name;
	public int age;
	public int weightInOz;
	public Coord2D location;

	/**
	 * The things a pet needs to provide, generically
	 *
	 * @param name
	 * @param age
	 * @param weightInOz
	 * @param location
	 */
	public Pet(String name, int age, int weightInOz, Coord2D location) {
		this.name = name;
		this.age = age;
		this.weightInOz = weightInOz;
		this.location = location;
	}

	/**
	 * Produces the name of this pet in the rescue
	 *
	 * @return the pet's name as a string
	 */
	public String getName() {
		return name;
	}

	/**
	 * the weight of the pet in Oz
	 *
	 * @return the weight rounded to the nearest oz
	 */
	public int getWeightInOz() {
		return weightInOz;
	}

	/**
	 * get the age of the pet
	 *
	 * @return the age of the pet in years
	 */
	public int getAge() {
		return age;
	}
}
