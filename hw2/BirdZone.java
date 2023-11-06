import java.util.LinkedList;

public class BirdZone extends Zone {
	public LinkedList<Bird> birds;
	public String pantryLabel = "Bird: 0 seeds";

	public BirdZone(LinkedList<Bird> birds) {
		this.birds = birds;
	}

	public LinkedList<? extends Pet> getPets() {
		return birds;
	}

	public String getPantryLabel() {
		return pantryLabel;
	}

	public int inHumanYears(String petName) {
		for (Bird bird : birds) {
			if (bird.getName() == petName) {
				return bird.getAge() * 9;
			}
		}
		return -1;
	}

	public Zone restockPetFood(String foodName, int foodAmt) {
		if (foodName == "seeds") {
			String[] split = pantryLabel.split(" ");
			int numSeeds = Integer.parseInt(split[1]);
			pantryLabel = "Bird: " + (numSeeds + foodAmt) + " seeds";

		}
		return this;
	}

	public Zone feedZone() {
		for (Bird bird : birds) {
			String[] split = pantryLabel.split(" ");

			int realSeedAmt = Integer.parseInt(split[1]) - bird.eats("seeds");
			int seedAmt = realSeedAmt < 0 ? 0 : realSeedAmt;

			pantryLabel = "Bird: " + seedAmt + " seeds";
		}
		return this;
	}
}
