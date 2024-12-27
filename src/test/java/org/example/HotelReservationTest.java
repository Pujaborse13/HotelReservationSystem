package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class HotelReservationTest {
    @Test
    public void givenValidDateRangeAndRewardCustomer_whenFindingCheapestBestRatedHotelUsingStreams_shouldReturnCorrectHotel() throws Exception {
        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);

        String[] dateRange = {"11Sep2020", "12Sep2020"};
        String customerType = "Reward";

        String result = reservation.findCheapestBestRatedHotelUsingStreams(dateRange, customerType);
        assertEquals("Ridgewood, Rating: 5 and Total Rates: $140", result);
    }

    @Test
    public void givenInvalidCustomerType_whenFindingHotelUsingStreams_shouldThrowException() {
        HotelReservation reservation = new HotelReservation();

        String[] dateRange = {"11Sep2020", "12Sep2020"};
        String customerType = "VIP";

        Exception exception = assertThrows(InvalidCustomerTypeException.class, () -> {
            reservation.findCheapestBestRatedHotelUsingStreams(dateRange, customerType);
        });
        assertEquals("Invalid customer type! Please use 'Regular' or 'Reward'.", exception.getMessage());
    }

    @Test
    public void givenInvalidDateFormat_whenFindingHotelUsingStreams_shouldThrowException() {
        HotelReservation reservation = new HotelReservation();

        String[] dateRange = {"11-09-2020", "12Sep2020"};
        String customerType = "Reward";

        Exception exception = assertThrows(InvalidDateFormatException.class, () -> {
            reservation.findCheapestBestRatedHotelUsingStreams(dateRange, customerType);
        });
        assertEquals("Invalid date format! Please use 'ddMMMyyyy' (e.g., 11Sep2020).", exception.getMessage());
          }
    }
