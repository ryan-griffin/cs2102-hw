import java.util.LinkedList;

/**
 * PetRescue is a class that implements the PetRescueable interface.
 * It contains methods that perform operations on the data stored in
 * the instance variables.
 */
public class PetRescue implements PetRescueable {
    /**
     * birdWeights: a list of bird weights in oz
     * dogYears: a list of dog ages in years
     * petOfTheMonth: the name of the pet of the month
     * pellets: the amount of pellets in the pantry
     * hay: the amount of hay in the pantry
     * catCoords: a list of cat coordinates
     */
    public LinkedList<Integer> birdWeights;
    public LinkedList<Integer> dogYears;
    public String petOfTheMonth;
    public int pellets = 0;
    public int hay = 0;
    public LinkedList<Coord> catCoords;

    /**
     * Constructor for PetRescue
     *
     * @param birdWeights   a list of bird weights in oz
     * @param dogYears      a list of dog ages in years
     * @param petOfTheMonth the name of the pet of the month
     * @param catCoords     a list of cat coordinates
     */
    public PetRescue(LinkedList<Integer> birdWeights,
            LinkedList<Integer> dogYears,
            String petOfTheMonth,
            LinkedList<Coord> catCoords) {
        this.birdWeights = birdWeights;
        this.dogYears = dogYears;
        this.petOfTheMonth = petOfTheMonth;
        this.catCoords = catCoords;
    }

    /**
     * Searches the birds in the pet rescue
     *
     * @return the size of the biggest bird in oz or 0 if there are no birds
     */
    public int biggestBird() {
        if (birdWeights.isEmpty()) {
            return 0;
        } else {
            int maxWeight = birdWeights.getFirst();
            for (int weight : birdWeights) {
                if (weight > maxWeight) {
                    maxWeight = weight;
                }
            }
            return maxWeight;
        }
    }

    /**
     * Transforms the dog records in the pet rescue
     *
     * @return a list of ages in the same order as the dog records,
     *         but each age is converted into human years by multiplying by 7
     */
    public LinkedList<Integer> inHumanYears() {
        LinkedList<Integer> humanYearsList = new LinkedList<>();

        for (Integer dogAge : dogYears) {
            int humanAge = dogAge * 7;
            humanYearsList.add(humanAge);
        }

        return humanYearsList;
    }

    /**
     * Awards the pet of the month with a title and/or a credential for their
     * accomplishments
     *
     * @param title      Can be a title like "Dr." or "Capt."
     *                   or the empty string "" if no title to be added
     * @param credential a degree or honor including but not limited to "MD", "PhD",
     *                   or "Esq."
     *                   or the empty string "" if no credential to be added
     *                   effects: modifies the name on record of the form "TITLE
     *                   name, CREDENTIAL"
     *                   multiple space separated titles can be added with the
     *                   newest first
     *                   multiple space separated credentials can be added after the
     *                   comma with the newest last.
     */
    public void bestowHonor(String title, String credential) {
        if (!title.isEmpty()) {
            petOfTheMonth = title + " " + petOfTheMonth;
        }
        if (!credential.isEmpty()) {
            if (petOfTheMonth.contains(",")) {
                petOfTheMonth += " " + credential;
            } else {
                petOfTheMonth += ", " + credential;
            }
        }
    }

    /**
     * Adds positive or subtracts negative food from the pantry and then prints out
     * a new label for the contents inside
     * assume the initial amount of pellets and hay should be 0
     *
     * @param pellets the amount of pellets to add/subtract
     * @param hay     the amount of hay to add/subtract
     * @return a string in the format "Chinchilla: # pellets, # hay"
     *         If the storage of pellets or hay goes negative, reset them to 0, and
     *         replace # with "unknown"
     */
    public String feedChinchillas(int pelletsToAdd, int hayToAdd) {
        pellets += pelletsToAdd;
        hay += hayToAdd;

        String label = "Chinchilla: " + (pellets >= 0 ? pellets : "unknown") + " pellets, " +
                (hay >= 0 ? hay : "unknown") + " hay";

        if (pellets < 0) {
            pellets = 0;
        }
        if (hay < 0) {
            hay = 0;
        }

        return label;
    }

    /**
     * Finds the closest cat in the rescue to some coordinate
     *
     * @param x the coordinate to find the closest to
     * @param y
     * @return the name of the cat if found or "Conspiciously Catless" if there are
     *         no cats.
     */
    public String closestTo(int x, int y) {
        if (catCoords.isEmpty()) {
            return "Conspiciously Catless";
        } else {
            String closestCat = null;
            double minDistance = Double.MAX_VALUE;

            for (Coord catCoord : catCoords) {
                int catX = catCoord.x;
                int catY = catCoord.y;
                double distance = Math.sqrt((x - catX) * (x - catX) + (y - catY) * (y - catY));

                if (distance < minDistance) {
                    minDistance = distance;
                    closestCat = catCoord.name;
                }
            }

            return closestCat;
        }
    }
}
