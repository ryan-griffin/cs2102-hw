import java.util.LinkedList;

public abstract class Zone implements Zoneable {

	/**
	 * @return the pets in a zone
	 */
	public abstract LinkedList<? extends Pet> getPets();

	/**
	 * @return the label of the pantry
	 */
	public abstract String getPantryLabel();

	/**
	 * Produces the number of pets in the zone
	 *
	 * @return the number of total pets in the zone
	 */
	public int petsInZone() {
		return this.getPets().size();
	}

	/**
	 * finds and produces the fattest animal available
	 *
	 * @return the heaviest pet (the one with the greatest weight according to its
	 *         getWeightInOz() method)
	 * @return null if there are no pets
	 */
	public Pet heaviestPet() {
		if (this.getPets().isEmpty()) {
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

	/**
	 * Fetch me that pet!
	 *
	 * @param petName the pet to look up in the zone, you may assume the pet is in
	 *                the zone
	 * @return the pet with that name or null if pet is not in the zone
	 */
	public Pet getPet(String petName) {
		for (Pet pet : this.getPets()) {
			if (pet.getName() == petName) {
				return pet;
			}
		}
		return null;
	}

	/**
	 * Gets the name of the pet that is closest to the given 3D coord
	 *
	 * @param x the 3D coord that only has the location information
	 * @param y
	 * @return the name of the pet closest to that coord
	 *         In the result of a tie, you may return any one
	 *         (we won't test ties)
	 *         if there are no pets, return null
	 *         assume the "z" coordinate we are using is 0 by default
	 *         (some pets may be up on perches)
	 *         return "No Pets Found" if there are no pets in the zone.
	 */
	public String closestPet(int x, int y) {
		if (this.getPets().isEmpty()) {
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
