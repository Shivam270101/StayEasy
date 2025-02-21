package com.stayeasy.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Booking")
@NoArgsConstructor
@Getter
@Setter
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long booking_id;
	
	  @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user; 
	
	@ManyToOne
	@JoinColumn(name = "room_id",nullable=false)
private Room room;
	
	@ManyToOne
	@JoinColumn(name = "hostel_id",nullable=false)
private Hostel hostel;


	
	@ManyToMany
	 @JsonIgnore
    @JoinTable(
        name = "booking_users",
        joinColumns = @JoinColumn(name = "booking_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
	 private List<User> users;
	 private LocalDateTime bookingDate;
	 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private LocalDateTime checkin;

	 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	 private LocalDateTime checkout;
@Enumerated(EnumType.STRING)
@Column(nullable = false)
private BookingStatus status;
	
}
//@ManyToOne
//@JoinColumn(name = "user_id",nullable=false)
//private User user;