package org.example;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelReservationTest {
    @Test
    public void givenHotelDetails_whenAdded_shouldBePresentInHotelList() {
        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110, 90);

        assertEquals(1, reservation.getHotels().size());
        assertEquals("Lakewood", reservation.getHotels().get(0).getName());
        assertEquals(110, reservation.getHotels().get(0).getWeekdayRate());
        assertEquals(90, reservation.getHotels().get(0).getWeekendRate());
    }
}
