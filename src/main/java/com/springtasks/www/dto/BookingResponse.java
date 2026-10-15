package com.springtasks.www.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.springtasks.www.entity.BookingStatus;

public class BookingResponse {

    public BookingResponse() {
		super();
	}

	public BookingResponse(Integer bookingId, Integer customerAuthUserId, Integer providerAuthUserId, Integer serviceId,
			String serviceName, BigDecimal amount, LocalDate bookingDate, String timeSlot, String serviceAddress,
			String city, String pincode, String notes, BookingStatus status, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.bookingId = bookingId;
		this.customerAuthUserId = customerAuthUserId;
		this.providerAuthUserId = providerAuthUserId;
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.amount = amount;
		this.bookingDate = bookingDate;
		this.timeSlot = timeSlot;
		this.serviceAddress = serviceAddress;
		this.city = city;
		this.pincode = pincode;
		this.notes = notes;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getCustomerAuthUserId() {
		return customerAuthUserId;
	}

	public void setCustomerAuthUserId(Integer customerAuthUserId) {
		this.customerAuthUserId = customerAuthUserId;
	}

	public Integer getProviderAuthUserId() {
		return providerAuthUserId;
	}

	public void setProviderAuthUserId(Integer providerAuthUserId) {
		this.providerAuthUserId = providerAuthUserId;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getServiceAddress() {
		return serviceAddress;
	}

	public void setServiceAddress(String serviceAddress) {
		this.serviceAddress = serviceAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BookingStatus getStatus() {
		return status;
	}

	public void setStatus(BookingStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	private Integer bookingId;

    private Integer customerAuthUserId;

    private Integer providerAuthUserId;

    private Integer serviceId;

    private String serviceName;

    private BigDecimal amount;

    private LocalDate bookingDate;

    private String timeSlot;

    private String serviceAddress;

    private String city;

    private String pincode;

    private String notes;

    private BookingStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}