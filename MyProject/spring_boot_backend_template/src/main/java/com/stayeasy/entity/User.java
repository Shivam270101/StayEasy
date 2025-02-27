package com.stayeasy.entity;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Collate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	
	private String FirstName;
	
	private String LastName;
	
	@Column(name="Email",nullable=false,unique=true)
	private String email;
	
	   @Enumerated(EnumType.STRING)
	@Column(name="UserType",nullable=false)
	private UserType usertype;
	   
	@Column(name="Password",nullable=false)
	private String password;
	
//	@Column(name="Confirm_Password",nullable=false)
//	private String confirmpassword;
	
	@Column(name="PhoneNo",nullable=false)
	private String phoneno;
	
	
	
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
	    private UserLocation location;
	   
	   @ManyToMany(mappedBy ="users",  cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	   @JsonIgnore
	   private List<Room> sharedrooms;
	   
//	   @ManyToOne
//	    @JoinColumn(name = "hostel_id")
//	    private Hostel hostel; // Hostels owned by the user (if OWNER)
	   
	    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JsonIgnore
	    private List<Booking> bookings;
	    
	    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	    @JsonIgnore
	    private List<Hostel> ownedHostels;
	    
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	   


	
	
	

}
