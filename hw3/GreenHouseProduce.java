import java.util.HashMap;
import java.util.List;

public class GreenHouseProduce extends AbsGreenHouse implements Sensible {
    public HashMap<Double, List<Double>> temperatures = new HashMap<>();
    public HashMap<Double, List<Double>> humidities = new HashMap<>();

    public void pollSensorData(List<Double> values) {

    }

    public TempHumidReading middleReading() {

    }

    public TempHumidReading middleReading(double onDate) {

    }
}
