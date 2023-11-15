import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreenHouseProduce extends AbsGreenHouse implements Sensible {
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

            double avgTemp = (temp1 + temp2) / 2;
            double avgHumidity = (humid1 + humid2) / 2;

            if (!data.containsKey(date)) {
                data.put(date, new ArrayList<Double>());
            }

            data.get(date).add(avgTemp);
            data.get(date).add(avgHumidity);
        }
    }

    @Override
    public TempHumidReading middleReading() {
        if (data.isEmpty()) {
            return new TempHumidReading(-999.0, -999.0);
        }

        double totalAvgTemp = 0;
        double totalAvgHumidity = 0;
        int count = 0;

        for (Map.Entry<Double, List<Double>> entry : data.entrySet()) {
            List<Double> readings = entry.getValue();

            for (int i = 0; i < readings.size(); i += 2) {
                totalAvgTemp += readings.get(i);
                totalAvgHumidity += readings.get(i + 1);
                count++;
            }
        }

        double overallAvgTemp = totalAvgTemp / count;
        double overallAvgHumidity = totalAvgHumidity / count;

        return new TempHumidReading(overallAvgTemp, overallAvgHumidity);
    }

    @Override
    public TempHumidReading middleReading(double onDate) {
        if (data.isEmpty() || !data.containsKey(onDate)) {
            return new TempHumidReading(-999.0, -999.0);
        }

        List<Double> readings = data.get(onDate);
        double totalAvgTemp = 0;
        double totalAvgHumidity = 0;

        for (int i = 0; i < readings.size(); i += 2) {
            totalAvgTemp += readings.get(i);
            totalAvgHumidity += readings.get(i + 1);
        }

        double overallAvgTemp = totalAvgTemp / (readings.size() / 2);
        double overallAvgHumidity = totalAvgHumidity / (readings.size() / 2);

        return new TempHumidReading(overallAvgTemp, overallAvgHumidity);
    }
}
