import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

public class GreenHouseNursery extends AbsGreenHouse implements Sensible {
    public GreenHouseNursery(GregorianCalendar calendar) {
        super(calendar);
    }

    /**
     * Reads an ordered sequence of data from the weather sensors to store in the
     * greenhouse
     * When called multiple times, appends the new readings after the current sensor
     * readings
     *
     * @param values An ordered sequence of [datetime, temperature, humidity,
     *               temperature, humidity, ..., datetime, temperature,
     *               humidity,....]
     *               - a date and time in yyyymmddHHMMSS format. E.g. 20231106183930
     *               for Nov 11, 2023, 6:39:30pm
     *               - temperature is either degrees Fahrenheit or -999 for an error
     *               case
     *               - humidity is either % from 0.0 to 100.0 or -999 for an error
     *               case
     *               Assume the sensor data always starts with a valid date
     *               The multiple temperature humidity pairs for a single datetime
     *               come from different plant sensors
     *               The values may skip dates and times when the sensors are off
     *               (you cannot assume that the date/time intervals will be
     *               regular)
     *               You *may* assume that the datetimes will be in ascending order
     */
    public void pollSensorData(List<Double> values) {
        if (values == null || values.isEmpty()) {
            return;
        }

        int i = 0;
        while (i < values.size()) {
            double dateTime = values.get(i++);
            if (isDateTime(dateTime)) {
                if (isDate(dateTime) && dateTime >= calendar.getTimeInMillis()) {
                    while (i < values.size() && isDateTime(values.get(i))) {
                        double temperature = values.get(i++);
                        double humidity = values.get(i++);

                        double dateKey = toDate(dateTime);
                        temperatures.computeIfAbsent(dateKey, k -> new ArrayList<>()).add(temperature);
                        humidities.computeIfAbsent(dateKey, k -> new ArrayList<>()).add(humidity);
                    }
                    calendar.setTimeInMillis((long) dateTime);
                } else {
                    i += (values.size() - i);
                }
            } else {
                i++;
            }
        }
    }

    /**
     * produces a pair of the middle temperature and humidity (respectively) from
     * the stored readings ignoring error values (-999s)
     *
     * @return a new SensorReading object that has the middle temperature of all the
     *         sensor values (value at index (size() / 2) of the sorted
     *         temperatures)
     *         and the middle humidity of the sorted humidities
     *         If there are no valid temperature or humidity values, respectively,
     *         then the resulting sensor reading should have -999 for that data
     */
    public TempHumidReading middleReading() {
        double middleTemperature = -999;
        double middleHumidity = -999;

        List<Double> validTemperatures = new ArrayList<>();
        List<Double> validHumidities = new ArrayList<>();

        for (List<Double> tempList : temperatures.values()) {
            for (Double temperature : tempList) {
                if (temperature != -999) {
                    validTemperatures.add(temperature);
                }
            }
        }

        for (List<Double> humidityList : humidities.values()) {
            for (Double humidity : humidityList) {
                if (humidity != -999) {
                    validHumidities.add(humidity);
                }
            }
        }

        Collections.sort(validTemperatures);
        Collections.sort(validHumidities);

        if (!validTemperatures.isEmpty()) {
            middleTemperature = validTemperatures.get(validTemperatures.size() / 2);
        }

        if (!validHumidities.isEmpty()) {
            middleHumidity = validHumidities.get(validHumidities.size() / 2);
        }

        return new TempHumidReading(middleTemperature, middleHumidity);
    }

    /**
     * produces a pair of the middle temperature and humidity (respectively) from
     * the stored readings ignoring error values (-999s)
     *
     * @param onDate the date which to consider medianReadings for (inclusive) with
     *               the format YYYYMMDD.0
     * @return a new SensorReading object that has the middle temperature of all the
     *         sensor values (value at index (size() / 2) of the sorted
     *         temperatures)
     *         and the middle humidity of the sorted humidities
     *         If there are no valid temperature or humidity values, respectively,
     *         then the resulting sensor reading should have -999 for that data
     */
    public TempHumidReading middleReading(double onDate) {
        double middleTemperature = -999;
        double middleHumidity = -999;

        List<Double> temperatureValues = temperatures.getOrDefault(onDate, new ArrayList<>());
        List<Double> humidityValues = humidities.getOrDefault(onDate, new ArrayList<>());

        List<Double> validTemperatures = new ArrayList<>();
        List<Double> validHumidities = new ArrayList<>();

        for (Double temperature : temperatureValues) {
            if (temperature != -999) {
                validTemperatures.add(temperature);
            }
        }

        for (Double humidity : humidityValues) {
            if (humidity != -999) {
                validHumidities.add(humidity);
            }
        }

        Collections.sort(validTemperatures);
        Collections.sort(validHumidities);

        if (!validTemperatures.isEmpty()) {
            middleTemperature = validTemperatures.get(validTemperatures.size() / 2);
        }

        if (!validHumidities.isEmpty()) {
            middleHumidity = validHumidities.get(validHumidities.size() / 2);
        }

        return new TempHumidReading(middleTemperature, middleHumidity);
    }
}
