package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class HotelReservation {
    private List<Hotel> hotels;

    public HotelReservation() {
        hotels = new ArrayList<>();
    }

    public void addHotel(String name, int weekdayRateRegular, int weekendRateRegular, int weekdayRateReward, int weekendRateReward, int rating) {
        Hotel hotel = new Hotel(name, weekdayRateRegular, weekendRateRegular, weekdayRateReward, weekendRateReward, rating);
        hotels.add(hotel);
    }

    public String findCheapestBestRatedHotel(String[] dateRange, String customerType) throws InvalidCustomerTypeException, InvalidDateFormatException {
        // Validate customer type
        if (!customerType.equalsIgnoreCase("Regular") && !customerType.equalsIgnoreCase("Reward")) {
            throw new InvalidCustomerTypeException("Invalid customer type! Please use 'Regular' or 'Reward'.");
        }

        // Validate date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMuuuu");
        try {
            for (String date : dateRange) {
                LocalDate.parse(date, formatter);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException("Invalid date format! Please use 'ddMMMyyyy' (e.g., 11Sep2020).");
        }

        boolean isRewardCustomer = customerType.equalsIgnoreCase("Reward");

        // Find cheapest and best-rated hotel
        return hotels.stream()
                .map(hotel -> {
                    int[] dayCounts = calculateDayCounts(dateRange, formatter);
                    int totalRate = (hotel.getWeekdayRate(isRewardCustomer) * dayCounts[0]) +
                            (hotel.getWeekendRate(isRewardCustomer) * dayCounts[1]);
                    return new HotelRate(hotel, totalRate);
                })
                .sorted((h1, h2) -> {
                    int rateComparison = Integer.compare(h1.getTotalRate(), h2.getTotalRate());
                    return rateComparison != 0 ? rateComparison : Integer.compare(h2.getHotel().getRating(), h1.getHotel().getRating());
                })
                .findFirst()
                .map(hotelRate -> hotelRate.getHotel().getName() +
                        ", Rating: " + hotelRate.getHotel().getRating() +
                        " and Total Rates: $" + hotelRate.getTotalRate())
                .orElse("No hotels available");
    }

    private int[] calculateDayCounts(String[] dateRange, DateTimeFormatter formatter) {
        int weekdayCount = 0;
        int weekendCount = 0;

        for (String dateStr : dateRange) {
            LocalDate date = LocalDate.parse(dateStr, formatter);
            DayOfWeek day = date.getDayOfWeek();
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                weekendCount++;
            } else {
                weekdayCount++;
            }
        }
        return new int[]{weekdayCount, weekendCount};
    }
}
/*

    public static void main( String[] args ) {
        System.out.println("Welcome to Hotel Reservation Program");

        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
        reservation.addHotel("Bridgewood",  150, 50, 110, 50, 4);
        reservation.addHotel("Ridgewood",  220, 150, 100, 40, 5);

        String[] dateRange = {"11Sep2020", "12Sep2020"};
        boolean isRewardCustomer = true;
        String result = reservation.findCheapestBestRatedHotel(dateRange, isRewardCustomer);
        System.out.println(result);
    }

}
*/

class HotelRate {
    private Hotel hotel;
    private int totalRate;

    public HotelRate(Hotel hotel, int totalRate) {
        this.hotel = hotel;
        this.totalRate = totalRate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public int getTotalRate() {
        return totalRate;
    }
}
