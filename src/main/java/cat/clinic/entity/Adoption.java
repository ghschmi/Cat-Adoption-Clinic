package cat.clinic.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/*
 * Adoption entity class
 */
@Entity
@Data
public class Adoption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adoptionId;
	
	@ManyToOne
	@JoinColumn(name = "cat_id", nullable = false)
//	@JsonBackReference
	private Cat cat;
	
	@ManyToOne
	@JoinColumn(name = "adopter_id", nullable = false)
//	@JsonBackReference
	private Adopter adopter;
	
	private LocalDate adoptionDate;
	private String status;  //pending, adopted
}
