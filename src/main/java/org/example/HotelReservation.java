package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HotelReservation {
    private List<Hotel> hotels;

    public HotelReservation() {
        hotels = new ArrayList<>();

    }

    public void addHotel(String name , int weekDayRate , int weekendDayRate, int rating) {
        Hotel hotel = new Hotel(name,weekDayRate,weekendDayRate,rating);
        hotels.add(hotel);
    }
    public String findCheapestHotel(String... dates) {
        Map<Hotel, Integer> hotelCosts = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");

        for (Hotel hotel : hotels) {
            int totalCost = 0;
            for (String date : dates) {
                LocalDate parsedDate = LocalDate.parse(date, formatter);
                DayOfWeek day = parsedDate.getDayOfWeek();
                if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                    totalCost += hotel.getWeekendRate();
                } else {
                    totalCost += hotel.getWeekdayRate();
                }
            }
            hotelCosts.put(hotel, totalCost);
        }
        int minCost = Collections.min(hotelCosts.values());
        List<String> cheapestHotels = new ArrayList<>();
        for (Map.Entry<Hotel, Integer> entry : hotelCosts.entrySet()) {
            if (entry.getValue() == minCost) {
                cheapestHotels.add(entry.getKey().getName());
            }
        }

        return String.join(" and ", cheapestHotels) + " with Total Rates $" + minCost;
    }

    public static void main( String[] args ) {
        System.out.println("Welcome to Hotel Reservation Program");

        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110, 90, 3);
        reservation.addHotel("Bridgewood", 160, 60, 4);
        reservation.addHotel("Ridgewood", 220, 150, 5);

        System.out.println("Cheapest Hotel: " + reservation.findCheapestHotel("11Sep2020", "12Sep2020"));

    }


}
