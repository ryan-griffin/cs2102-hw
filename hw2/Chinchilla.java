public class Chinchilla extends Pet {
	public int dustBathResidueInOz;

	public Chinchilla(String name, int age, int weightInOz, Coord2D location, int dustBathResidueInOz) {
		super(name, age, weightInOz, location);
		this.dustBathResidueInOz = dustBathResidueInOz;
	}

	public int getWeightInOz() {
		return weightInOz + dustBathResidueInOz;
	}

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
