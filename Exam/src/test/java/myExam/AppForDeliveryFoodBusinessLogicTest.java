package myExam;

import myExam.AppForDeliveryFood;
import myExam.Courier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppForDeliveryFoodBusinessLogicTest {

    @Test
    public void testAddCourier() {
        AppForDeliveryFood app = new AppForDeliveryFood();
        app.addCourier("Courier 1");
        assertEquals(1, app.getCouriers().size());
    }

    @Test
    public void testIsFreeCourierWhenAllFree() {
        AppForDeliveryFood app = new AppForDeliveryFood();
        Courier courier1 = new Courier("Courier 1");
        courier1.setAvailable(true);
        Courier courier2 = new Courier("Courier 2");
        courier2.setAvailable(true);

        app.getCouriers().addAll(courier1, courier2);

        assertTrue(app.isFreeCourier(), String.valueOf(true));
    }


}
