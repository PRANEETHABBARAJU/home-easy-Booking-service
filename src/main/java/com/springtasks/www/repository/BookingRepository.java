package com.springtasks.www.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springtasks.www.entity.Booking;
import com.springtasks.www.entity.BookingStatus;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

	List<Booking> findByCustomerAuthUserIdOrderByCreatedAtDesc(Integer customerAuthUserId);
	List<Booking> findByProviderAuthUserIdOrderByCreatedAtDesc(Integer providerAuthUserId);
	List<Booking>findByServiceIdAndStatusOrderByCreatedAtAsc(Integer serviceId ,BookingStatus status);
	Optional<Booking>findByBookingIdAndCustomerAuthUserId(Integer bookingId ,Integer customerAuthUserId);
	Optional<Booking>findByBookingIdAndProviderAuthUserId(Integer bookingId,Integer providerAuthUserId);
}
