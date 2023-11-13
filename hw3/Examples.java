import org.junit.Test;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.*;

public class Examples {
    LinkedList<Double> data = new LinkedList<>();
    List<Double> sensorData = List.of(20231113010101.0, 45.5, 34.0, 46.6, 40.0,
            20231130020202.0, 22.2, 20.0, 35.5, 30.0,
            20231130040202.0, 23.2, 19.0, 34.5, 30.0,
            20231130060202.0, 20.2, 26.0, 35.5, 30.0,
            20231227020202.0, 30.2, 20.4, 36.5, 31.4,
            -999.0, 31.0, 32.2, -999.0);

    public Examples() {
        data.add(20231106010101.0);
        for (int i = 0; i < 1000; i++) {
            data.add(i * 4.0);
        }
    }

    @Test
    public void testSTHRConstructorNoArg() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading();
        assertEquals(-999.0, sthrtest.temperature, 0d);
        assertEquals(-999.0, sthrtest.humidity, 0d);
    }

    @Test
    public void testSTHRConstructorTwoArg() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading(40.4, 12.3);
        assertEquals(40.4, sthrtest.temperature, 0d);
        assertEquals(12.3, sthrtest.humidity, 0d);
    }

    @Test
    public void testTHRConstructorTwoArg() {
        TempHumidReading thrtest = new TempHumidReading(42.4, 16.3);
        assertEquals(42.4, thrtest.temperature, 0d);
        assertEquals(16.3, thrtest.humidity, 0d);
    }

    @Test
    public void testSTHRConstructorCopy() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHRTest = new SuperTempHumidReading(sthrtest);
        assertEquals(sthrtest.temperature, copySTHRTest.temperature, 0.0);
        assertEquals(sthrtest.humidity, copySTHRTest.humidity, 0.0);
    }

    @Test
    public void testSTHREqualsExact() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHRTest = new SuperTempHumidReading(sthrtest);
        assertEquals(sthrtest, copySTHRTest);
    }

    @Test
    public void testSTHREqualsInExact() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHRTest = new SuperTempHumidReading(40.40001, 12.30001);
        assertEquals(sthrtest, copySTHRTest);
    }

    @Test
    public void testSTHREqualsFail() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHRTest = new SuperTempHumidReading(41, 1);
        assertNotEquals(sthrtest, copySTHRTest);
    }

    @Test
    public void testSTHREqualsInExactCopy() {
        TempHumidReading sthrtest = new TempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHRTest = new SuperTempHumidReading(sthrtest);
        sthrtest = new SuperTempHumidReading(40.40001, 12.30001);
        assertEquals(copySTHRTest, sthrtest);
    }

    @Test
    public void testSTHREqualsFailCopy() {
        TempHumidReading sthrtest = new TempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHRTest = new SuperTempHumidReading(sthrtest);
        sthrtest = new SuperTempHumidReading(41, 1);
        assertNotEquals(sthrtest, copySTHRTest);
    }

    @Test
    public void testSTHREqualsNonTHR() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading(40.4, 12.3);
        String work = "{40.4F;12.3%}";
        assertNotEquals(sthrtest, work);
    }

    @Test
    public void testSTHRtoStringNotError() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading(40.4, 12.3);
        assertEquals("{40.4F;12.3%}", sthrtest.toString());
        sthrtest = new SuperTempHumidReading(40.4254, 12.34);
        assertEquals("{40.4F;12.3%}", sthrtest.toString());
    }

    @Test
    public void testSTHRtoStringBothError() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading();
        assertEquals("{Err;Err}", sthrtest.toString());
    }

    @Test
    public void testSTHRtoStringHumidError() {
        SuperTempHumidReading sthrtest = new SuperTempHumidReading(50.0, -999.0);
        assertEquals("{50.0F;Err}", sthrtest.toString());
    }

    @Test
    public void testGHNMiddleReadingNoDateNotEmpty() {
        GreenHouseNursery testghn = new GreenHouseNursery();
        testghn.pollSensorData(sensorData);
        SuperTempHumidReading ref = new SuperTempHumidReading(34.5, 30.0);
        assertEquals(ref, testghn.middleReading());
    }

    @Test
    public void testGHNMiddleReadingNoDateEmpty() {
        GreenHouseNursery testghn = new GreenHouseNursery();
        SuperTempHumidReading ref = new SuperTempHumidReading();
        assertEquals(ref, testghn.middleReading());
    }

    @Test
    public void testGHNMiddleReadingDateNotEmpty() {
        GreenHouseNursery testghn = new GreenHouseNursery();
        testghn.pollSensorData(sensorData);
        SuperTempHumidReading ref = new SuperTempHumidReading(34.5, 30.0);
        assertEquals(ref, testghn.middleReading(20231130.0));
    }

    @Test
    public void testGHPMiddleReadingNoDateNotEmpty() {
        GreenHouseProduce testghp = new GreenHouseProduce();
        testghp.pollSensorData(sensorData);
        SuperTempHumidReading ref = new SuperTempHumidReading(34.5, 30.0);
        assertEquals(ref, testghp.middleReading());
    }

    @Test
    public void testGHPMiddleReadingNoDateEmpty() {
        GreenHouseProduce testghp = new GreenHouseProduce();
        SuperTempHumidReading ref = new SuperTempHumidReading();
        assertEquals(ref, testghp.middleReading());
    }

    @Test
    public void testGHPMiddleReadingDateNotEmpty() {
        GreenHouseProduce testghp = new GreenHouseProduce();
        testghp.pollSensorData(sensorData);
        SuperTempHumidReading ref = new SuperTempHumidReading(34.5, 30.0);
        assertEquals(ref, testghp.middleReading(20231130.0));
    }

    @Test
    public void testGHPMiddleReadingDateEmpty() {
        GreenHouseProduce testghp = new GreenHouseProduce();
        SuperTempHumidReading ref = new SuperTempHumidReading();
        assertEquals(ref, testghp.middleReading(20231130.0));
    }

    @Test
    public void testSuperTempHumidReadingEqualsNew() {
        assertEquals(new SuperTempHumidReading(), new SuperTempHumidReading());
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
