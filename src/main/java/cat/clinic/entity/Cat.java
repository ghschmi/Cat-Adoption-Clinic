package cat.clinic.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/*
 * Cat entity class
 */
@Entity
@Data
public class Cat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long catId;
	
	private String catName;
	private String age;
	private String breed;
	private String healthStatus;
	private String description;
	private String adoptionStatus; //available, unavailable, pending, adopted
	
	@EqualsAndHashCode.Exclude  //these three annotations are used to prevent recursion 
	@ToString.Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "cat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Adoption> adoptions = new HashSet<>();
}
