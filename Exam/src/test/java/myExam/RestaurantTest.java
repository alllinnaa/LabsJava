package myExam;

import org.junit.Test;
import static org.junit.Assert.*;

public class RestaurantTest {

    @Test
    public void testCalculateTotalPrice() {
        Restaurant restaurant = new Restaurant("Test Restaurant");

        restaurant.addMenuItem(new MenuItem("Pizza", 15.99));
        restaurant.addMenuItem(new MenuItem("Burger", 9.99));
        restaurant.addMenuItem(new MenuItem("Salad", 7.49));

        double expectedTotalPrice = 15.99 + 9.99 + 7.49;

        double actualTotalPrice = restaurant.calculateTotalPrice();

        assertEquals(expectedTotalPrice, actualTotalPrice, 0.001);
    }
}

