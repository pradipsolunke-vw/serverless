package com.example.mybookingapp.service;


import com.example.mybookingapp.domain.Seat;

import java.util.List;

public interface BookingService {


    Seat bookASeat(Seat seat);

    List<Seat> findAll();

    void cancelBooking(Integer seatId);
}
