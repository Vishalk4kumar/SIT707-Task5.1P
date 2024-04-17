package sit707_week5;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WeatherControllerTest {

    private static WeatherController wController;
    private static double[] hourlyTemperatures;

    @BeforeClass
    public static void setUp() {
        wController = WeatherController.getInstance();
        hourlyTemperatures = new double[wController.getTotalHours()];
        for (int i = 0; i < hourlyTemperatures.length; i++) {
            hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDown() {
        wController.close();
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "222342946";
        assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Vishal";
        assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTemperatureMin() {
        System.out.println("+++ testTemperatureMin +++");
        double minTemperature = findMinTemperature();
        assertEquals(minTemperature, wController.getTemperatureMinFromCache(), 0.01);
    }

    @Test
    public void testTemperatureMax() {
        System.out.println("+++ testTemperatureMax +++");
        double maxTemperature = findMaxTemperature();
        assertEquals(maxTemperature, wController.getTemperatureMaxFromCache(), 0.01);
    }

    @Test
    public void testTemperatureAverage() {
        System.out.println("+++ testTemperatureAverage +++");
        double averageTemperature = calculateAverageTemperature();
        assertEquals(averageTemperature, wController.getTemperatureAverageFromCache(), 0.01);
    }

    private double findMinTemperature() {
        double minTemperature = Double.MAX_VALUE;
        for (double temperature : hourlyTemperatures) {
            if (minTemperature > temperature) {
                minTemperature = temperature;
            }
        }
        return minTemperature;
    }

    private double findMaxTemperature() {
        double maxTemperature = Double.MIN_VALUE;
        for (double temperature : hourlyTemperatures) {
            if (maxTemperature < temperature) {
                maxTemperature = temperature;
            }
        }
        return maxTemperature;
    }

    private double calculateAverageTemperature() {
        double sumTemperature = 0;
        for (double temperature : hourlyTemperatures) {
            sumTemperature += temperature;
        }
        return sumTemperature / hourlyTemperatures.length;
    }
}
