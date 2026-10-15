package com.springtasks.www.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springtasks.www.dto.BookingResponse;
import com.springtasks.www.dto.CreateBookingRequest;
import com.springtasks.www.dto.UpdateBookingStatusRequest;
import com.springtasks.www.entity.Booking;
import com.springtasks.www.entity.BookingStatus;
import com.springtasks.www.exceptionHandling.BookingNotFoundException;
import com.springtasks.www.exceptionHandling.InvalidBookingOperationException;
import com.springtasks.www.repository.BookingRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(
            BookingRepository bookingRepository) {

        this.bookingRepository = bookingRepository;
    }

    // Customer creates a new booking
    public BookingResponse createBooking(
            Integer customerAuthUserId,
            CreateBookingRequest request) {

        Booking booking = new Booking();

        booking.setCustomerAuthUserId(
            customerAuthUserId
        );

        booking.setProviderAuthUserId(null);
        booking.setServiceId(request.getServiceId());
        booking.setServiceName(request.getServiceName());
        booking.setAmount(request.getAmount());
        booking.setBookingDate(request.getBookingDate());
        booking.setTimeSlot(request.getTimeSlot());

        booking.setServiceAddress(
            request.getServiceAddress()
        );

        booking.setCity(request.getCity());
        booking.setPincode(request.getPincode());
        booking.setNotes(request.getNotes());
        booking.setStatus(BookingStatus.PENDING);

        Booking savedBooking =
            bookingRepository.save(booking);

        return convertToResponse(savedBooking);
    }

    // Customer gets all their bookings
    public List<BookingResponse> getCustomerBookings(
            Integer customerAuthUserId) {

        List<Booking> bookings =
            bookingRepository
                .findByCustomerAuthUserIdOrderByCreatedAtDesc(
                    customerAuthUserId
                );

        List<BookingResponse> responses =
            new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(convertToResponse(booking));
        }

        return responses;
    }

    // Customer gets one of their bookings by booking ID
    public BookingResponse getCustomerBookingById(
            Integer customerAuthUserId,
            Integer bookingId) {

        Booking booking = bookingRepository
            .findByBookingIdAndCustomerAuthUserId(
                bookingId,
                customerAuthUserId
            )
            .orElseThrow(() ->
                new BookingNotFoundException(
                    "Booking not found with ID: "
                        + bookingId
                )
            );

        return convertToResponse(booking);
    }

    // Customer cancels a pending booking
    public BookingResponse cancelBooking(
            Integer customerAuthUserId,
            Integer bookingId) {

        Booking booking = bookingRepository
            .findByBookingIdAndCustomerAuthUserId(
                bookingId,
                customerAuthUserId
            )
            .orElseThrow(() ->
                new BookingNotFoundException(
                    "Booking not found with ID: "
                        + bookingId
                )
            );

        if (booking.getStatus()
                != BookingStatus.PENDING) {

            throw new InvalidBookingOperationException(
                "Only pending bookings can be cancelled"
            );
        }

        booking.setStatus(BookingStatus.CANCELLED);

        Booking cancelledBooking =
            bookingRepository.save(booking);

        return convertToResponse(cancelledBooking);
    }

    // Provider gets pending bookings for a service type
    public List<BookingResponse> getAvailableBookings(
            Integer serviceId) {

        List<Booking> bookings =
            bookingRepository
                .findByServiceIdAndStatusOrderByCreatedAtAsc(
                    serviceId,
                    BookingStatus.PENDING
                );

        List<BookingResponse> responses =
            new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(convertToResponse(booking));
        }

        return responses;
    }

    // Provider accepts a pending booking
    public BookingResponse acceptBooking(
            Integer providerAuthUserId,
            Integer bookingId) {

        Booking booking = bookingRepository
            .findById(bookingId)
            .orElseThrow(() ->
                new BookingNotFoundException(
                    "Booking not found with ID: "
                        + bookingId
                )
            );

        if (booking.getStatus()
                != BookingStatus.PENDING) {

            throw new InvalidBookingOperationException(
                "Only pending bookings can be accepted"
            );
        }

        booking.setProviderAuthUserId(
            providerAuthUserId
        );

        booking.setStatus(BookingStatus.ACCEPTED);

        Booking acceptedBooking =
            bookingRepository.save(booking);

        return convertToResponse(acceptedBooking);
    }

    // Provider gets all bookings assigned to them
    public List<BookingResponse> getProviderBookings(
            Integer providerAuthUserId) {

        List<Booking> bookings =
            bookingRepository
                .findByProviderAuthUserIdOrderByCreatedAtDesc(
                    providerAuthUserId
                );

        List<BookingResponse> responses =
            new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(convertToResponse(booking));
        }

        return responses;
    }

    // Provider updates booking status to in progress or completed
    public BookingResponse updateBookingStatus(
            Integer providerAuthUserId,
            Integer bookingId,
            UpdateBookingStatusRequest request) {

        Booking booking = bookingRepository
            .findByBookingIdAndProviderAuthUserId(
                bookingId,
                providerAuthUserId
            )
            .orElseThrow(() ->
                new BookingNotFoundException(
                    "Assigned booking not found with ID: "
                        + bookingId
                )
            );

        BookingStatus currentStatus =
            booking.getStatus();

        BookingStatus requestedStatus =
            request.getStatus();

        boolean validTransition =
            (currentStatus == BookingStatus.ACCEPTED
                && requestedStatus
                    == BookingStatus.IN_PROGRESS)
            ||
            (currentStatus == BookingStatus.IN_PROGRESS
                && requestedStatus
                    == BookingStatus.COMPLETED);

        if (!validTransition) {
            throw new InvalidBookingOperationException(
                "Invalid booking status change from "
                    + currentStatus
                    + " to "
                    + requestedStatus
            );
        }

        booking.setStatus(requestedStatus);

        Booking updatedBooking =
            bookingRepository.save(booking);

        return convertToResponse(updatedBooking);
    }

    // Admin gets all customer bookings
    public List<BookingResponse> getAllBookings() {

        List<Booking> bookings =
            bookingRepository.findAll();

        List<BookingResponse> responses =
            new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(convertToResponse(booking));
        }

        return responses;
    }

    // Converts Booking entity into BookingResponse DTO
    private BookingResponse convertToResponse(
            Booking booking) {

        return new BookingResponse(
            booking.getBookingId(),
            booking.getCustomerAuthUserId(),
            booking.getProviderAuthUserId(),
            booking.getServiceId(),
            booking.getServiceName(),
            booking.getAmount(),
            booking.getBookingDate(),
            booking.getTimeSlot(),
            booking.getServiceAddress(),
            booking.getCity(),
            booking.getPincode(),
            booking.getNotes(),
            booking.getStatus(),
            booking.getCreatedAt(),
            booking.getUpdatedAt()
        );
    }
}