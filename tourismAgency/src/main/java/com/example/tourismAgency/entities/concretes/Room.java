package com.example.tourismAgency.entities.concretes;

import com.example.tourismAgency.entities.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "Lazy" })
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "number_of_rooms")
	private int numberOfRooms;
	
//	@Column(name = "is_booked", columnDefinition = "BOOLEAN DEFAULT FALSE")
//	private boolean isBooked = false;

//	@Column(name = "number_of_vacant_rooms")
//	private int numberOfVacantRooms = this.numberOfRooms;

	@Enumerated
	@Column(name = "room_type")
	private RoomType roomType;

	@Column(name = "number_of_beds")
	private int numberOfBeds;

	@Column(name = "square_meter")
	private int squareMeter;

	@Column(name = "tv")
	private boolean hasTv;

	@Column(name = "minibar")
	private boolean hasMinibar;

	@Column(name = "game_console")
	private boolean hasGameConsole;

	@Column(name = "price_first_period_for_adult")
	private int priceFirstPeriodForAdult;

	@Column(name = "price_first_period_for_child")
	private int priceFirstPeriodForChild;

	@Column(name = "price_second_period_for_adult")
	private int priceSecondPeriodForAdult;

	@Column(name = "price_second_period_for_child")
	private int priceSecondPeriodForChild;

	@ManyToOne
	@JoinColumn(name = "hotel_id", foreignKey = @ForeignKey(name = "hotel_id"), referencedColumnName = "id")
	private Hotel hotel;

}
