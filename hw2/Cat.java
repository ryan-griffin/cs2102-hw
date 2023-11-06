public class Cat extends Animal {
	public boolean hasBeenPetToday;

	public Cat(String name, int age, int weightInOz, Coord2D location, boolean hasBeenPetToday) {
		super(name, age, weightInOz, location);
		this.hasBeenPetToday = hasBeenPetToday;
	}

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
