package com.example.mybookingapp.service;

import com.example.mybookingapp.domain.Seat;
import com.example.mybookingapp.repository.BookingRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    private BookingRepo bookingRepo;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Seat bookASeat(Seat seat) {
        return bookingRepo.bookASeat(seat);
    }

    @Override
    public List<Seat> findAll() {
        return bookingRepo.getBookings();
    }

    @Override
    public void cancelBooking(Integer seatId) {
        bookingRepo.cancelBooking(seatId);
    }

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
    }
}
