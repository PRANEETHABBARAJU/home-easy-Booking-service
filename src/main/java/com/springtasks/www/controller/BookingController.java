package com.springtasks.www.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springtasks.www.dto.BookingResponse;
import com.springtasks.www.dto.CreateBookingRequest;
import com.springtasks.www.dto.UpdateBookingStatusRequest;
import com.springtasks.www.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
@CrossOrigin(originPatterns = {
	    "http://localhost:*",
	    "http://127.0.0.1:*"
	})
public class BookingController {

    private final BookingService bookingService;

    public BookingController(
            BookingService bookingService) {

        this.bookingService = bookingService;
    }

    // Customer creates a new booking
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @AuthenticationPrincipal
            Integer customerAuthUserId,
            @Valid @RequestBody
            CreateBookingRequest request) {

        BookingResponse response =
            bookingService.createBooking(
                customerAuthUserId,
                request
            );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(response);
    }

    // Customer gets all their bookings
    @GetMapping("/customer/me")
    public ResponseEntity<List<BookingResponse>>
            getCustomerBookings(
                @AuthenticationPrincipal
                Integer customerAuthUserId) {

        List<BookingResponse> response =
            bookingService.getCustomerBookings(
                customerAuthUserId
            );

        return ResponseEntity.ok(response);
    }

    // Customer gets one of their bookings
    @GetMapping("/customer/me/{bookingId}")
    public ResponseEntity<BookingResponse>
            getCustomerBookingById(
                @AuthenticationPrincipal
                Integer customerAuthUserId,
                @PathVariable Integer bookingId) {

        BookingResponse response =
            bookingService.getCustomerBookingById(
                customerAuthUserId,
                bookingId
            );

        return ResponseEntity.ok(response);
    }

    // Customer cancels a pending booking
    @PutMapping("/customer/{bookingId}/cancel")
    public ResponseEntity<BookingResponse> cancelBooking(
            @AuthenticationPrincipal
            Integer customerAuthUserId,
            @PathVariable Integer bookingId) {

        BookingResponse response =
            bookingService.cancelBooking(
                customerAuthUserId,
                bookingId
            );

        return ResponseEntity.ok(response);
    }

    // Provider views pending bookings for a service
    @GetMapping("/provider/available")
    public ResponseEntity<List<BookingResponse>>
            getAvailableBookings(
                @RequestParam Integer serviceId) {

        List<BookingResponse> response =
            bookingService.getAvailableBookings(
                serviceId
            );

        return ResponseEntity.ok(response);
    }

    // Provider accepts a pending booking
    @PutMapping("/provider/{bookingId}/accept")
    public ResponseEntity<BookingResponse> acceptBooking(
            @AuthenticationPrincipal
            Integer providerAuthUserId,
            @PathVariable Integer bookingId) {

        BookingResponse response =
            bookingService.acceptBooking(
                providerAuthUserId,
                bookingId
            );

        return ResponseEntity.ok(response);
    }

    // Provider gets all assigned bookings
    @GetMapping("/provider/me")
    public ResponseEntity<List<BookingResponse>>
            getProviderBookings(
                @AuthenticationPrincipal
                Integer providerAuthUserId) {

        List<BookingResponse> response =
            bookingService.getProviderBookings(
                providerAuthUserId
            );

        return ResponseEntity.ok(response);
    }

    // Provider updates an assigned booking status
    @PutMapping("/provider/{bookingId}/status")
    public ResponseEntity<BookingResponse>
            updateBookingStatus(
                @AuthenticationPrincipal
                Integer providerAuthUserId,
                @PathVariable Integer bookingId,
                @Valid @RequestBody
                UpdateBookingStatusRequest request) {

        BookingResponse response =
            bookingService.updateBookingStatus(
                providerAuthUserId,
                bookingId,
                request
            );

        return ResponseEntity.ok(response);
    }

    // Admin gets every booking
    @GetMapping("/admin/all")
    public ResponseEntity<List<BookingResponse>>
            getAllBookings() {

        List<BookingResponse> response =
            bookingService.getAllBookings();

        return ResponseEntity.ok(response);
    }
}