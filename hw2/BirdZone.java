import java.util.LinkedList;

public class BirdZone extends Zone {
	public LinkedList<Bird> birds;

	public BirdZone(LinkedList<Bird> birds) {
		this.birds = birds;
	}

	public LinkedList<? extends Petable> getPets() {
		return birds;
	}

	public int inHumanYears(String petName) {
		for (Bird bird : birds) {
			if (bird.getName() == petName) {
				return bird.getAge() * 9;
			}
		}
		return -1;
	}
}
