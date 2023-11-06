import java.util.LinkedList;

public class CatZone extends Zone {
	public LinkedList<Cat> cats;
	public String pantryLabel = "Cat: 0 cans, 0 treats";

	public CatZone(LinkedList<Cat> cats) {
		this.cats = cats;
	}

	public LinkedList<? extends Pet> getPets() {
		return cats;
	}

	public String getPantryLabel() {
		return pantryLabel;
	}

	public int inHumanYears(String petName) {
		for (Cat cat : cats) {
			if (cat.getName() == petName) {
				return cat.getAge() * 6;
			}
		}
		return -1;
	}

	public Zone restockPetFood(String foodName, int foodAmt) {
		switch (foodName) {
			case "cans":
				String[] split = pantryLabel.split(" ");
				int numCans = Integer.parseInt(split[1]);
				pantryLabel = "Cat: " + (numCans + foodAmt) + " cans, " + split[3] + " treats";
			case "treats":
				split = pantryLabel.split(" ");
				int numTreats = Integer.parseInt(split[3]);
				pantryLabel = "Cat: " + split[1] + " cans, " + (numTreats + foodAmt) + " treats";
		}
		return this;
	}

	public Zone feedZone() {
		for (Cat cat : cats) {
			String[] split = pantryLabel.split(" ");

			int realCanAmt = Integer.parseInt(split[1]) - cat.eats("cans");
			int canAmt = realCanAmt < 0 ? 0 : realCanAmt;

			int realTreatAmt = Integer.parseInt(split[3]) - cat.eats("treats");
			int treatAmt = realTreatAmt < 0 ? 0 : realTreatAmt;

			pantryLabel = "Cat: " + canAmt + " cans, " + treatAmt + " treats";
		}
		return this;
	}
}
