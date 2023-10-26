import java.util.LinkedList;

public class PetRescue implements PetRescueable {
    public String petOfTheMonth;

    public PetRescue(LinkedList<Integer> birdWeights,
            LinkedList<Integer> dogYears,
            String petOfTheMonth,
            LinkedList<Coord> catCoords) {
    }

    @Override
    public int biggestBird() {
        throw new UnsupportedOperationException("Unimplemented method 'biggestBird'");
    }

    @Override
    public LinkedList<Integer> inHumanYears() {
        throw new UnsupportedOperationException("Unimplemented method 'inHumanYears'");
    }

    @Override
    public void bestowHonor(String title, String credential) {
        throw new UnsupportedOperationException("Unimplemented method 'bestowHonor'");
    }

    @Override
    public String feedChinchillas(int pellets, int hay) {
        throw new UnsupportedOperationException("Unimplemented method 'feedChinchillas'");
    }

    @Override
    public String closestTo(int x, int y) {
        throw new UnsupportedOperationException("Unimplemented method 'closestTo'");
    }
}
