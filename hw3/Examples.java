import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Examples {
    List<Double> values = List.of(20231106010101.0, 45.5, 34.0, 46.6, 40.0, 20231130020202.0, 22.2, 20.0, 35.5, 30.0,
            -999.0, 31.0, 32.2, -999.0);
    LinkedList<Double> data = new LinkedList<>();

    public Examples() {
        data.add(20231106010101.0);
        for (int i = 0; i < 1000; i++) {
            data.add(i * 4.0);
        }
    }

    @Test
    public void testSuperTempHumidReadingEqualsNew() {
        assertEquals(new SuperTempHumidReading(), new SuperTempHumidReading());
    }

    @Test
    public void testSuperTempHumidReadingToString() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(80.0, 33.3);
        assertEquals("{80.0F;33.3%}", sthr.toString());
    }

    @Test
    public void testSuperTempHumidReadingCopyConstructor() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(80.0, 33.3);
        assertEquals(sthr, new SuperTempHumidReading(sthr));
    }

    @Test
    public void testTimePollSensorData() {
        GreenHouseNursery nursery = new GreenHouseNursery();
        GreenHouseProduce produce = new GreenHouseProduce();

        long time1 = System.currentTimeMillis();
        nursery.pollSensorData(data);
        long time2 = System.currentTimeMillis();
        produce.pollSensorData(data);
        long time3 = System.currentTimeMillis();
        System.out.println(String.format("computation1() : computation2() :: %s : %s", time2 - time1, time3 - time2));
        assertTrue(time2 - time1 < time3 - time2); // assert that computation 1 is faster
    }

}
