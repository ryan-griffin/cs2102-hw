import java.util.LinkedList;

public class ChinchillaZone extends Zone {
	public LinkedList<Chinchilla> chinchillas;
	public String pantryLabel = "Chinchilla: 0 pellets, 0 hay";

	public ChinchillaZone(LinkedList<Chinchilla> chinchillas) {
		this.chinchillas = chinchillas;
	}

	public LinkedList<? extends Pet> getPets() {
		return chinchillas;
	}

	public String getPantryLabel() {
		return pantryLabel;
	}

	public int inHumanYears(String petName) {
		for (Chinchilla chinchilla : chinchillas) {
			if (chinchilla.getName() == petName) {
				return chinchilla.getAge() * 10;
			}
		}
		return -1;
	}

	public Zone restockPetFood(String foodName, int foodAmt) {
		switch (foodName) {
			case "pellets":
				String[] split = pantryLabel.split(", ");
				int numPellets = Integer.parseInt(split[0].split(" ")[1]);
				pantryLabel = "Chinchilla: " + (numPellets + foodAmt) + " pellets, " + split[1];
			case "hay":
				split = pantryLabel.split(", ");
				int numHay = Integer.parseInt(split[1].split(" ")[0]);
				pantryLabel = "Chinchilla: " + split[0] + ", " + (numHay + foodAmt) + " hay";
		}
		return this;
	}

	public Zone feedZone() {
		for (Chinchilla chinchilla : chinchillas) {
			String[] split = pantryLabel.split(", ");

			int realPelletAmt = Integer.parseInt(split[0].split(" ")[1]) - chinchilla.eats("pellets");
			int pelletAmt = realPelletAmt < 0 ? 0 : realPelletAmt;

			int realHayAmt = Integer.parseInt(split[1].split(" ")[0]) - chinchilla.eats("hay");
			int hayAmt = realHayAmt < 0 ? 0 : realHayAmt;

			pantryLabel = "Chinchilla: " + pelletAmt + " pellets, " + hayAmt + " hay";
		}
		return this;
	}
}
