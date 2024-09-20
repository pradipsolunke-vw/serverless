package com.example.mybookingapp;

import com.example.mybookingapp.domain.Seat;
import com.example.mybookingapp.dto.Request;
import com.example.mybookingapp.dto.Response;
import com.example.mybookingapp.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.adapter.aws.FunctionInvoker;
import org.springframework.cloud.function.context.FunctionRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SpringBootApplication
public class MyBookingAppApplication {

    ObjectMapper mapper = new ObjectMapper();


    @Autowired
    BookingService bookingService;

    public static void main(String[] args) {
        SpringApplication.run(MyBookingAppApplication.class, args);
    }

    @Bean
    @Secured("ROLE_USER")
    public Function<Request, Response> createBooking() {
        return request -> {
            String username = request.getUsername();
            // buissness logic to fetch user roles from B2B using username
            String role ="ROLE_USER";// consider it comes from B2B
            if(username.equals("admin")){
                System.out.println(request);
                Seat seat = mapper.convertValue(request.getBody(), Seat.class);
                Seat resp = bookingService.bookASeat(seat);
                Response response = new Response();
                response.setBody(resp);
                response.setHttpStatus(HttpStatus.CREATED);
                return response;
            }else {
                Response response = new Response();
                response.setHttpStatus(HttpStatus.FORBIDDEN);
                response.setMessage("You don't have the required role");
                return response;
            }
        };

    }

//    @Bean
//    public FunctionInvoker functionInvoker(FunctionRegistry registry) {
//        registry.register(FunctionRegistration.<Request, Response>builder()
//                .id("createBooking") // Set a unique identifier
//                .function(createBooking()) // Reference the function
//                .type(FunctionType.REQUEST_RESPONSE) // Specify the function type
//                .build());
//        return functionInvoker;
//    }


}
