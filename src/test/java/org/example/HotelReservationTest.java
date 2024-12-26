package org.example;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

public class HotelReservationTest {
    @Test
    public void givenDateRange_shouldReturnCheapestHotel() {
        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110, 90, 3);
        reservation.addHotel("Bridgewood", 150, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 5);

        String result = reservation.findCheapestHotel("11Sep2020", "12Sep2020");
        assertEquals("Lakewood and Bridgewood with Total Rates $200", result);
             }
    }
