import java.util.LinkedList;

public class ChinchillaZone extends Zone {
	public LinkedList<Chinchilla> chinchillas;

	public ChinchillaZone(LinkedList<Chinchilla> chinchillas) {
		this.chinchillas = chinchillas;
	}

	public LinkedList<? extends Petable> getPets() {
		return chinchillas;
	}

	public int inHumanYears(String petName) {
		for (Chinchilla chinchilla : chinchillas) {
			if (chinchilla.getName() == petName) {
				return chinchilla.getAge() * 10;
			}
		}
		return -1;
	}
}
