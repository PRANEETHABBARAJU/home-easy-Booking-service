package com.springtasks.www.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateBookingRequest {

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

	@NotNull(message = "Service ID is required")
    private Integer serviceId;

    public CreateBookingRequest() {
		super();
	}

	@NotBlank(message = "Service name is required")
    @Size(
        max = 100,
        message = "Service name cannot exceed 100 characters"
    )
    private String serviceName;

    @NotNull(message = "Amount is required")
    @DecimalMin(
        value = "0.0",
        inclusive = true,
        message = "Amount cannot be negative"
    )
    @Digits(
        integer = 8,
        fraction = 2,
        message = "Amount can contain up to 8 digits and 2 decimal places"
    )
    private BigDecimal amount;

    @NotNull(message = "Booking date is required")
    @FutureOrPresent(
        message = "Booking date cannot be in the past"
    )
    private LocalDate bookingDate;

    @NotBlank(message = "Time slot is required")
    @Size(
        max = 50,
        message = "Time slot cannot exceed 50 characters"
    )
    private String timeSlot;

    @NotBlank(message = "Service address is required")
    @Size(
        max = 255,
        message = "Service address cannot exceed 255 characters"
    )
    private String serviceAddress;

    @NotBlank(message = "City is required")
    @Size(
        max = 100,
        message = "City cannot exceed 100 characters"
    )
    private String city;

    @NotBlank(message = "Pincode is required")
    @Pattern(
        regexp = "^[0-9]{6}$",
        message = "Pincode must contain 6 digits"
    )
    private String pincode;

    @Size(
        max = 500,
        message = "Notes cannot exceed 500 characters"
    )
    private String notes;
}