package com.example.mybookingapp.repository;


import com.example.mybookingapp.domain.Seat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookingRepo {

    private List<Seat> seats = new ArrayList<>(10);

    public Seat bookASeat (Seat seat){
        seats.add(seat);
        return seat;
    }

    public List<Seat> getBookings (){
        return seats;
    }

    public void cancelBooking (int seatNumber){
        Seat seat = seats.stream().filter(obj -> obj.getSeatNumber() == seatNumber).findFirst().get();
        seats.remove(seat);
    }

}
