package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class HotelReservationTest {
    @Test
    public void givenDateRange_whenFindingCheapestHotelForRewardCustomer_shouldReturnCorrectHotel() {
        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        String[] dateRange = {"11Sep2020", "12Sep2020"};
        boolean isRewardCustomer = true;

        String result = reservation.findCheapestHotel(dateRange, isRewardCustomer);

        assertEquals("Ridgewood, Rating: 5 and Total Rates: $140", result);
    }
    }
