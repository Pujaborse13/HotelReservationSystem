package org.example;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class HotelReservationTest {
    @Test
    public void givenHotels_whenAdded_shouldContainRatings() {
        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110, 90, 3);
        reservation.addHotel("Bridgewood", 150, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 5);

        assertEquals(3, reservation.getHotels().size());
        assertEquals(3, reservation.getHotels().get(0).getRating());
        assertEquals(4, reservation.getHotels().get(1).getRating());
        assertEquals(5, reservation.getHotels().get(2).getRating());
        }
    }
