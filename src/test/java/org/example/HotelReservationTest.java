package org.example;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HotelReservationTest {
    @Test
    public void givenHotelDetails_whenAdded_shouldContainWeekdayAndWeekendRates() {
        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110, 90, 3);
        reservation.addHotel("Bridgewood", 150, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 5);

        List<Hotel> hotels = reservation.getHotels();

        assertEquals(3, hotels.size());
        assertEquals(110, hotels.get(0).getWeekdayRate());
        assertEquals(90, hotels.get(0).getWeekendRate());
        assertEquals(150, hotels.get(1).getWeekdayRate());
        assertEquals(50, hotels.get(1).getWeekendRate());
    }
    }
