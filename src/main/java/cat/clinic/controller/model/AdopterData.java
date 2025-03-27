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
 * DTO for an adopter
 */
@Data
@NoArgsConstructor
public class AdopterData {

	private Long adopterId;
	private String adopterName;
	private String adopterEmail;
	private String adoptionHistory;
	private Set<AdoptionResponse> adoptions = new HashSet<>();

	public AdopterData(Adopter adopter) {
		adopterId = adopter.getAdopterId();
		adopterName = adopter.getAdopterName();
		adopterEmail = adopter.getAdopterEmail();
		adoptionHistory = adopter.getAdoptionHistory();

		for (Adoption adoption : adopter.getAdoptions()) {
			adoptions.add(new AdoptionResponse(adoption));
		}
	}

	@Data
	@NoArgsConstructor
	static class AdoptionResponse { // inner class for adoption details
		private Long adoptionId;
		private Cat cat;
		private LocalDate adoptionDate;
		private String status;

		AdoptionResponse(Adoption adoption) {
			adoptionId = adoption.getAdoptionId();
			adoptionDate = adoption.getAdoptionDate();
			status = adoption.getStatus();
			cat = adoption.getCat();

		}
	}
}
