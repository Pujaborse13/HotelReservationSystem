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

    public String findCheapestBestRatedHotel(String[] dateRange) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMuuuu");
        int[] dayCounts = calculateDayCounts(dateRange, formatter);

        return hotels.stream()
                .map(hotel -> new HotelRate(hotel, (hotel.getWeekdayRate() * dayCounts[0]) + (hotel.getWeekendRate() * dayCounts[1])))
                .sorted(Comparator.comparing(HotelRate::getTotalRate).thenComparing((h1, h2) -> Integer.compare(h2.getHotel().getRating(), h1.getHotel().getRating())))
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


    public List<Hotel> getHotels() {
        return hotels;
    }

    public void displayHotels() {
        hotels.forEach(System.out::println);
    }


    public static void main( String[] args ) {
        System.out.println("Welcome to Hotel Reservation Program");

        HotelReservation reservation = new HotelReservation();
        reservation.addHotel("Lakewood", 110, 90, 3);
        reservation.addHotel("Bridgewood", 160, 60, 4);
        reservation.addHotel("Ridgewood", 220, 150, 5);

        String[] dateRange = {"11Sep2020", "12Sep2020"};
        String result = reservation.findCheapestBestRatedHotel(dateRange);
        System.out.println(result);
    }


}


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
