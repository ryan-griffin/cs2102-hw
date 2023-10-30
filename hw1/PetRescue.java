import java.util.LinkedList;

public class PetRescue implements PetRescueable {
    public LinkedList<Integer> birdWeights;
    public LinkedList<Integer> dogYears;
    public String petOfTheMonth;
    public int pellets = 0;
    public int hay = 0;
    public LinkedList<Coord> catCoords;

    public PetRescue(LinkedList<Integer> birdWeights,
            LinkedList<Integer> dogYears,
            String petOfTheMonth,
            LinkedList<Coord> catCoords) {
        this.birdWeights = birdWeights;
        this.dogYears = dogYears;
        this.petOfTheMonth = petOfTheMonth;
        this.catCoords = catCoords;
    }

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

    public LinkedList<Integer> inHumanYears() {
        LinkedList<Integer> humanYearsList = new LinkedList<>();

        for (Integer dogAge : dogYears) {
            int humanAge = dogAge * 7;
            humanYearsList.add(humanAge);
        }

        return humanYearsList;
    }

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
