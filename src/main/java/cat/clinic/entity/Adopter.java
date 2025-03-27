package cat.clinic.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*
 * Adopter entity class
 */
@Entity
@Data
public class Adopter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long adopterId;
	
	private String adopterName;
	
	@Column(unique = true) //can't have two of the same emails 
	private String adopterEmail;
	
	private String adoptionHistory;
	
	@EqualsAndHashCode.Exclude  //these annotations are used to prevent recursion 
	@ToString.Exclude
//	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "adopter", cascade = CascadeType.ALL)
	private Set<Adoption> adoptions = new HashSet<>();
}
