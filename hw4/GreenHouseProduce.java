import java.util.HashMap;
import java.util.List;
import java.util.GregorianCalendar;

public class GreenHouseProduce extends AbsGreenHouse implements Sensible {
    public HashMap<Double, List<Double>> temperatures = new HashMap<>();
    public HashMap<Double, List<Double>> humidities = new HashMap<>();

    public GreenHouseProduce(GregorianCalendar calendar) {
        super(calendar);
    }

    public void pollSensorData(List<Double> values) {

    }

    public TempHumidReading middleReading() {
        return new TempHumidReading(-999, -999);
    }

    public TempHumidReading middleReading(double onDate) {
        return new TempHumidReading(-999, -999);
    }
}
