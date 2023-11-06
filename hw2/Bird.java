public class Bird extends Animal {
	public boolean wingsClipped;

	public Bird(String name, int age, int weightInOz, Coord2D location, boolean wingsClipped) {
		super(name, age, weightInOz, location);
		this.wingsClipped = wingsClipped;
	}

	public int eats(String foodLabel) {
		return foodLabel == "seeds" ? 1 : 0;
	}
}
