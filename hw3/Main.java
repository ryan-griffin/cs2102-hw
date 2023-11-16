import java.util.List;

public class Main {
	public static void main(String[] args) {
		GreenHouseNursery ghn = new GreenHouseNursery();
		List<Double> sensorData = List.of(20231113010101.0, 45.5, 34.0, 46.6, 40.0,
				20231130020202.0, 22.2, 20.0, 35.5, 30.0,
				20231130040202.0, 23.2, 19.0, 34.5, 30.0,
				20231130060202.0, 20.2, 26.0, 35.5, 30.0,
				20231227020202.0, 30.2, 20.4, 36.5, 31.4,
				-999.0, 31.0, 32.2, -999.0);

		ghn.pollSensorData(sensorData);
		System.out.println(ghn.temperatures);
		System.out.println(ghn.humidities);
	}
}
