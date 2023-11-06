import java.util.LinkedList;

public abstract class Zone implements Zoneable {
	public abstract LinkedList<? extends Pet> getPets();

	public abstract String getPantryLabel();

	public int petsInZone() {
		return this.getPets().size();
	}

	public Pet heaviestPet() {
		if (this.getPets().size() == 0) {
			return null;
		}

		Pet heaviest = this.getPets().get(0);
		for (Pet pet : this.getPets()) {
			if (pet.getWeightInOz() > heaviest.getWeightInOz()) {
				heaviest = pet;
			}
		}
		return heaviest;
	}

	public Pet getPet(String petName) {
		for (Pet pet : this.getPets()) {
			if (pet.getName() == petName) {
				return pet;
			}
		}
		return null;
	}

	public String closestPet(int x, int y) {
		if (this.getPets().size() == 0) {
			return "No Pets Found";
		}

		Pet closest = this.getPets().get(0);
		for (Pet pet : this.getPets()) {
			if (Math.sqrt(Math.pow(pet.location.x - x, 2) + Math.pow(pet.location.y - y, 2)) < Math
					.sqrt(Math.pow(closest.location.x - x, 2) + Math.pow(closest.location.y - y, 2))) {
				closest = pet;
			}
		}
		return closest.getName();
	}
}
