package com.example.tourismAgency.entities.concretes;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "number_of_adult")
	private int numberOfAdult;
	
	@Column(name = "number_of_child")
	private int numberOfChild;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	private LocalDate endDate;
	
	@Column(name = "created_date")
	private LocalDate createdDate = LocalDate.now();
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean isActive = true;
	
	@OneToOne
	@JoinColumn(name = "room_id", foreignKey = @ForeignKey(name = "room_id"))
	private Room room;
	
	@OneToOne
	@JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "customer_id"))
	private Customer customer;
	
}
