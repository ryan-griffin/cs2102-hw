public abstract class Animal implements Petable {
	public String name;
	public int age;
	public int weightInOz;
	public Coord2D location;

	public Animal(String name, int age, int weightInOz, Coord2D location) {
		this.name = name;
		this.age = age;
		this.weightInOz = weightInOz;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public int getWeightInOz() {
		return weightInOz;
	}

	public int getAge() {
		return age;
	}
}
