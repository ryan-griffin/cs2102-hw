import java.util.LinkedList;

public abstract class Zone implements Zoneable {
	public abstract LinkedList<? extends Petable> getPets();

	public int petsInZone() {
		return this.getPets().size();
	}

	public Petable heaviestPet() {
		Petable heaviest = this.getPets().get(0);
		for (Petable pet : this.getPets()) {
			if (pet.getWeightInOz() > heaviest.getWeightInOz()) {
				heaviest = pet;
			}
		}
		return heaviest;
	}

	public Zoneable restockPetFood(String foodName, int foodAmt) {
		return this;
	}

	public Zoneable feedZone() {
		return this;
	}

	public Petable getPet(String petName) {
		for (Petable pet : this.getPets()) {
			if (pet.getName().equals(petName)) {
				return pet;
			}
		}
		return null;
	}

	public String getPantryLabel() {
		return null;
	}

	public String closestPet(int x, int y) {
		return null;
	}
}
