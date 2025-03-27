package cat.clinic.controller.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import cat.clinic.entity.Adopter;
import cat.clinic.entity.Adoption;
import cat.clinic.entity.Cat;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO for a cat
 */
@Data
@NoArgsConstructor
public class CatData {
	private Long catId;
	private String catName;
	private String age;
	private String breed;
	private String healthStatus;
	private String description;
	private String adoptionStatus;
	private Set<AdoptionResponse> adoptions = new HashSet<>();
	
	public CatData(Cat cat) {
		catId = cat.getCatId();
		catName = cat.getCatName();
		age = cat.getAge();
		breed = cat.getBreed();
		healthStatus = cat.getHealthStatus();
		description = cat.getDescription();
		adoptionStatus = cat.getAdoptionStatus();
		
	for (Adoption adoption : cat.getAdoptions()) {
		adoptions.add(new AdoptionResponse(adoption));
	}
}

@Data
@NoArgsConstructor
static class AdoptionResponse { // inner class for adoption details
	private Long adoptionId;
	private Adopter adopter;
	private LocalDate adoptionDate;
	private String status;

	AdoptionResponse(Adoption adoption) {
		adoptionId = adoption.getAdoptionId();
		adoptionDate = adoption.getAdoptionDate();
		status = adoption.getStatus();
		adopter = adoption.getAdopter();

	}
}

}
