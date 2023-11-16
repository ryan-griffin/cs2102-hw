public class SuperTempHumidReading extends TempHumidReading {
    public SuperTempHumidReading(double temp, double humid) {
        super(temp, humid);
    }

    public SuperTempHumidReading() {
        super(-999, -999);
    }

    public SuperTempHumidReading(TempHumidReading object) {
        super(object.temperature, object.humidity);
    }

    /**
     * checks whether two TempHumidReadings are equal to each other (if they're
     * within 0.001 from each other)
     *
     * @param object1 - first object to compare
     * @param object2 - second object to compare
     * @return - a boolean representing whether the objects are equal or not.
     */

    public boolean equals(TempHumidReading object1, TempHumidReading object2) {
        double humidDiff = Math.abs(object1.humidity - object2.humidity);
        double tempDiff = Math.abs(object1.temperature - object2.temperature);

        return humidDiff <= 0.001 && tempDiff <= 0.001;
    }

    /**
     * converts a TempHumidReading to a string of the format "{98.6F;36%}"
     *
     * @param object1 - the TempHumidReading that will be converted to a string
     * @return - a string containing the data.
     */

    public String toString() {
        double temperature = this.temperature;
        double humidity = this.humidity;

        String tempString = String.format("%,.1f", temperature);
        String humString = String.format("%,.1f", humidity);

        tempString = temperature == -999.0 ? "{Err;" : "{" + tempString + "F;";
        humString = humidity == -999.0 ? "Err}" : humString + "%}";

        return tempString + humString;
    }
}
