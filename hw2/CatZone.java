import java.util.LinkedList;

public class CatZone extends Zone {
	public LinkedList<Cat> cats;

	public CatZone(LinkedList<Cat> cats) {
		this.cats = cats;
	}

	public LinkedList<? extends Petable> getPets() {
		return cats;
	}

	public int inHumanYears(String petName) {
		for (Cat cat : cats) {
			if (cat.getName() == petName) {
				return cat.getAge() * 6;
			}
		}
		return -1;
	}
}
