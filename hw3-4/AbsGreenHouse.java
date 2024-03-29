import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 * An abstract superclass to provide template methods for performance specific
 * subclasses.
 */
public abstract class AbsGreenHouse implements QualityControlable {
    protected GregorianCalendar calendar;
    protected HashMap<Double, List<Double>> temperatures = new HashMap<>();
    protected HashMap<Double, List<Double>> humidities = new HashMap<>();

    public AbsGreenHouse() {
        calendar = new GregorianCalendar();
    }

    public AbsGreenHouse(GregorianCalendar calendar) {
        this.calendar = (GregorianCalendar) calendar.clone();
    }

    /**
     * computes the current percentage of non-datetime sensor values that are
     * -999.0s
     *
     * @return a percent value between 0.0 and 100.0 inclusive
     */
    public double percentError() {
        double total = 0.0;
        double error = 0.0;
        for (Double date : temperatures.keySet()) {
            for (Double temp : temperatures.get(date)) {
                if (temp == -999.0) {
                    error++;
                }
                total++;
            }
        }
        for (Double date : humidities.keySet()) {
            for (Double humidity : humidities.get(date)) {
                if (humidity == -999.0) {
                    error++;
                }
                total++;
            }
        }

        return (total != 0.0) ? (error / total) * 100.0 : total;
    }

    // GIVEN CODE
    /**
     * Assume a sensor value is a date if it is greater jan 01, 1970
     *
     * @param sensorDatum the datum which may be a date, datetime, temperature, or
     *                    humidity
     * @return true if it is a formatted date number
     */
    public boolean isDate(double sensorDatum) {
        return sensorDatum > 19700101.0;
    }

    /**
     * Assume a sensor value is a date if it is greater jan 01, 1970 00:00:00
     * represented as a double
     *
     * @param sensorDatum the datum which may be a date, datetime, temperature, or
     *                    humidity
     * @return true if it is a formatted date number
     */
    public boolean isDateTime(double sensorDatum) {
        return sensorDatum > 19700101000000.0;
    }

    /**
     * Converts the double date time format to just the date part by dividing and
     * rounding
     *
     * @param dateTime YYYYMMDDhhmmss.0
     * @return YYYYMMDD.0
     */
    public double toDate(double dateTime) {
        return Math.floor(dateTime / 1000000.0); // convert YYYYMMDDhhmmss -> YYYYMMDD
    }

    /**
     * compares two YYYYMMDD.0 for equality
     *
     * @param date1 one YYYYMMDD.0
     * @param date2 another YYYYMMDD.0
     * @return true if they are within some error tolerance (0.001) of each other
     */
    public boolean sameDate(double date1, double date2) {
        return Math.abs(date1 - date2) < 0.001;
    }
}
