import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GreenHouseNursery extends AbsGreenHouse implements Sensible {
    public HashMap<Double, List<Double>> data = new HashMap<Double, List<Double>>();

    @Override
    public void pollSensorData(List<Double> values) {
        if (values.isEmpty()) {
            return;
        }

        for (int i = 0; i < values.size(); i += 5) {
            double date = values.get(i);
            double temp1 = values.get(i + 1);
            double humid1 = values.get(i + 2);
            double temp2 = values.get(i + 3);
            double humid2 = values.get(i + 4);

            List<Double> readings = new ArrayList<Double>();
            readings.add(temp1);
            readings.add(humid1);
            readings.add(temp2);
            readings.add(humid2);

            data.put(date, readings);
        }
    }

    @Override
    public TempHumidReading middleReading() {
        if (data.isEmpty()) {
            return new TempHumidReading(-999.0, -999.0);
        }

        double totalTemp = 0;
        double totalHumidity = 0;
        int count = 0;

        for (List<Double> readings : data.values()) {
            for (int i = 0; i < readings.size(); i += 2) {
                totalTemp += readings.get(i);
                totalHumidity += readings.get(i + 1);
                count++;
            }
        }

        double overallAvgTemp = totalTemp / count;
        double overallAvgHumidity = totalHumidity / count;

        return new TempHumidReading(overallAvgTemp, overallAvgHumidity);
    }

    @Override
    public TempHumidReading middleReading(double onDate) {
        if (data.isEmpty() || !data.containsKey(onDate)) {
            return new TempHumidReading(-999.0, -999.0);
        }

        List<Double> readings = data.get(onDate);
        double totalTemp = 0;
        double totalHumidity = 0;

        for (int i = 0; i < readings.size(); i += 2) {
            totalTemp += readings.get(i);
            totalHumidity += readings.get(i + 1);
        }

        double avgTemp = totalTemp / (readings.size() / 2);
        double avgHumidity = totalHumidity / (readings.size() / 2);

        return new TempHumidReading(avgTemp, avgHumidity);
    }
}
