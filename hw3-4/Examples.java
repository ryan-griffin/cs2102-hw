import org.junit.Test;

import java.util.GregorianCalendar;
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
    public void testSTHREqualsNew() {
        assertEquals(new SuperTempHumidReading(), new SuperTempHumidReading());
    }

    @Test
    public void testSTHRConstructorNoArg() {
        SuperTempHumidReading sthr = new SuperTempHumidReading();
        assertEquals(-999.0, sthr.temperature, 0d);
        assertEquals(-999.0, sthr.humidity, 0d);
    }

    @Test
    public void testSTHRConstructorTwoArg() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(40.4, 12.3);
        assertEquals(40.4, sthr.temperature, 0d);
        assertEquals(12.3, sthr.humidity, 0d);
    }

    @Test
    public void testTHRConstructorTwoArg() {
        TempHumidReading thr = new TempHumidReading(42.4, 16.3);
        assertEquals(42.4, thr.temperature, 0d);
        assertEquals(16.3, thr.humidity, 0d);
    }

    @Test
    public void testSTHRConstructorCopy() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHR = new SuperTempHumidReading(sthr);
        assertEquals(sthr.temperature, copySTHR.temperature, 0.0);
        assertEquals(sthr.humidity, copySTHR.humidity, 0.0);
    }

    @Test
    public void testSTHREqualsExact() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHR = new SuperTempHumidReading(sthr);
        assertEquals(sthr, copySTHR);
    }

    @Test
    public void testSTHREqualsInExact() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHR = new SuperTempHumidReading(40.40001, 12.30001);
        assertEquals(sthr, copySTHR);
    }

    @Test
    public void testSTHREqualsFail() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHR = new SuperTempHumidReading(41, 1);
        assertNotEquals(sthr, copySTHR);
    }

    @Test
    public void testSTHREqualsInExactCopy() {
        TempHumidReading sthr = new TempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHR = new SuperTempHumidReading(sthr);
        sthr = new SuperTempHumidReading(40.40001, 12.30001);
        assertEquals(copySTHR, sthr);
    }

    @Test
    public void testSTHREqualsFailCopy() {
        TempHumidReading sthr = new TempHumidReading(40.4, 12.3);
        SuperTempHumidReading copySTHR = new SuperTempHumidReading(sthr);
        sthr = new SuperTempHumidReading(41, 1);
        assertNotEquals(sthr, copySTHR);
    }

    @Test
    public void testSTHREqualsNonTHR() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(40.4, 12.3);
        assertNotEquals("{40.4F;12.3%}", sthr);
    }

    @Test
    public void testSTHRtoStringNotError() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(40.4, 12.3);
        assertEquals("{40.4F;12.3%}", sthr.toString());
        sthr = new SuperTempHumidReading(40.4254, 12.34);
        assertEquals("{40.4F;12.3%}", sthr.toString());
    }

    @Test
    public void testSTHRtoStringBothError() {
        SuperTempHumidReading sthr = new SuperTempHumidReading();
        assertEquals("{Err;Err}", sthr.toString());
    }

    @Test
    public void testSTHRtoStringHumidError() {
        SuperTempHumidReading sthr = new SuperTempHumidReading(50.0, -999.0);
        assertEquals("{50.0F;Err}", sthr.toString());
    }

    @Test
    public void testGHNMiddleReadingNoDateNotEmpty() {
        GregorianCalendar calendar = new GregorianCalendar(2022, 5, 7);
        GreenHouseNursery ghn = new GreenHouseNursery(calendar);
        ghn.pollSensorData(sensorData);
        SuperTempHumidReading sthr = new SuperTempHumidReading(34.5, 30.0);
        assertEquals(sthr, ghn.middleReading());
    }

    @Test
    public void testGHNMiddleReadingNoDateEmpty() {
        GregorianCalendar calendar = new GregorianCalendar(2022, 5, 7);
        GreenHouseNursery ghn = new GreenHouseNursery(calendar);
        SuperTempHumidReading sthr = new SuperTempHumidReading();
        assertEquals(sthr, ghn.middleReading());
    }

    @Test
    public void testGHPMiddleReadingNoDateNotEmpty() {
        GregorianCalendar calendar = new GregorianCalendar(2022, 5, 7);
        GreenHouseProduce ghp = new GreenHouseProduce(calendar);
        ghp.pollSensorData(sensorData);
        SuperTempHumidReading sthr = new SuperTempHumidReading(34.5, 30.0);
        assertEquals(sthr, ghp.middleReading());
    }

    @Test
    public void testGHPMiddleReadingNoDateEmpty() {
        GregorianCalendar calendar = new GregorianCalendar(2022, 5, 7);
        GreenHouseProduce ghp = new GreenHouseProduce(calendar);
        SuperTempHumidReading sthr = new SuperTempHumidReading();
        assertEquals(sthr, ghp.middleReading());
    }

    @Test
    public void testGHPMiddleReadingDateEmpty() {
        GregorianCalendar calendar = new GregorianCalendar(2022, 5, 7);
        GreenHouseProduce ghp = new GreenHouseProduce(calendar);
        SuperTempHumidReading sthr = new SuperTempHumidReading();
        assertEquals(sthr, ghp.middleReading(20231130.0));
    }

    @Test
    public void testTimePollSensorData() {
        GregorianCalendar calendar = new GregorianCalendar(2022, 5, 7);
        GreenHouseNursery nursery = new GreenHouseNursery(calendar);
        GreenHouseProduce produce = new GreenHouseProduce(calendar);

        long time1 = System.currentTimeMillis();
        nursery.pollSensorData(data);
        long time2 = System.currentTimeMillis();
        produce.pollSensorData(data);
        long time3 = System.currentTimeMillis();
        System.out.println(String.format("computation1() : computation2() :: %s : %s", time2 - time1, time3 - time2));
        assertTrue(time2 - time1 < time3 - time2); // assert that computation 1 is faster
    }

    @Test
    public void testPercentError() {
        GregorianCalendar calendar = new GregorianCalendar(2022, 5, 7);
        GreenHouseNursery nursery = new GreenHouseNursery(calendar);
        nursery.pollSensorData(sensorData);
        assertEquals(8.3, nursery.percentError(), 0.1);
    }

    @Test
    public void testGHNPriorDate() {
        GregorianCalendar calendar = new GregorianCalendar(2023, 11, 28);
        GreenHouseNursery nursery = new GreenHouseNursery(calendar);
        nursery.pollSensorData(sensorData);
        SuperTempHumidReading sthr = new SuperTempHumidReading();
        assertEquals(sthr, nursery.middleReading(20231130.0));
    }

    @Test
    public void testGNHCalendarClone() {
        GregorianCalendar calendar = new GregorianCalendar(2023, 11, 28);
        GreenHouseNursery nursery = new GreenHouseNursery(calendar);
        nursery.pollSensorData(sensorData);
        calendar.set(2022, 11, 28);
        SuperTempHumidReading sthr = new SuperTempHumidReading();
        assertEquals(sthr, nursery.middleReading(20231130.0));
    }
}
