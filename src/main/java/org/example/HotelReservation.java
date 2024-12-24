package org.example;

import java.util.ArrayList;
import java.util.List;

public class HotelReservation
{
    private List<Hotel> hotels;

    public HotelReservation()
    {
        hotels = new ArrayList<>();

    }

    public void addHotel(String name , int weekDayRate , int weekendDayRate)
    {
        Hotel hotel = new Hotel(name,weekDayRate,weekendDayRate);
        hotels.add(hotel);

    }

    public List<Hotel> getHotels()
    {
        return hotels;
    }

    public static void main( String[] args )
    {
        System.out.println( "Welcome to Hotel Reservation Program" );

        HotelReservation reservation = new HotelReservation();

        reservation.addHotel("Lakewood", 110, 90);
        reservation.addHotel("Bridgewood", 160, 60);
        reservation.addHotel("Ridgewood", 220, 150);

        System.out.println("Hotels added: " + reservation.getHotels());
    }
}
