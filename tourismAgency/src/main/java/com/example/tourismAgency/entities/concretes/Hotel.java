package com.example.tourismAgency.entities.concretes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hotels")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "Lazy"})
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@EqualsAndHashCode(callSuper = false)
public class Hotel extends User{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;


	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "star")
	private int star;

	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();

	@Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean isActive = true;
	
	

	// Entity Relationship

	@OneToOne
	@JoinColumn(name = "city_id", referencedColumnName = "id")
	private City city;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "hotel_facilities", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "facility_id"))
	private List<Facility> facilities = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "hotel_hostelTypes", joinColumns = @JoinColumn(name = "hotel_id"), inverseJoinColumns = @JoinColumn(name = "hostel_type_id"))
	private List<HostelType> hostelTypes = new ArrayList<>();

	@OneToMany(mappedBy = "hotel", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Room> rooms;
	
}
