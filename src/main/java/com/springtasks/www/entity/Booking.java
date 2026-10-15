package com.springtasks.www.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Integer bookingId;

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

	public Booking(Integer bookingId, Integer customerAuthUserId, Integer providerAuthUserId, Integer serviceId,
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

	public Booking() {
		super();
	}

	@Column(
        name = "customer_auth_user_id",
        nullable = false
    )
    private Integer customerAuthUserId;

    @Column(name = "provider_auth_user_id")
    private Integer providerAuthUserId;

    @Column(name = "service_id", nullable = false)
    private Integer serviceId;

    @Column(
        name = "service_name",
        nullable = false,
        length = 100
    )
    private String serviceName;

    @Column(
        name = "amount",
        nullable = false,
        precision = 10,
        scale = 2
    )
    private BigDecimal amount;

    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    @Column(
        name = "time_slot",
        nullable = false,
        length = 50
    )
    private String timeSlot;

    @Column(
        name = "service_address",
        nullable = false,
        length = 255
    )
    private String serviceAddress;

    @Column(
        name = "city",
        nullable = false,
        length = 100
    )
    private String city;

    @Column(
        name = "pincode",
        nullable = false,
        length = 10
    )
    private String pincode;

    @Column(name = "notes", length = 500)
    private String notes;

    @Enumerated(EnumType.STRING)
    @Column(
        name = "status",
        nullable = false,
        length = 20
    )
    private BookingStatus status = BookingStatus.PENDING;

    @Column(
        name = "created_at",
        insertable = false,
        updatable = false
    )
    private LocalDateTime createdAt;

    @Column(
        name = "updated_at",
        insertable = false,
        updatable = false
    )
    private LocalDateTime updatedAt;
}