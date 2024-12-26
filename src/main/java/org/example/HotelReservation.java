package org.example;

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

        System.out.println("Hotels with their ratings:");
        reservation.displayHotels();
    }


}
