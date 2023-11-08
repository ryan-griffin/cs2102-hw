public class SuperTempHumidReading extends TempHumidReading {
    public SuperTempHumidReading(double temperature, double humidity) {
        super(temperature, humidity);
    }

    public SuperTempHumidReading() {
        super(-999, -999);
    }

    public SuperTempHumidReading(TempHumidReading thr) {
        super(thr.temperature, thr.humidity);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
