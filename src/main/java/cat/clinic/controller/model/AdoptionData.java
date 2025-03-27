package cat.clinic.controller.model;

import java.time.LocalDate;

import cat.clinic.entity.Adoption;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * DTO for adoptions 
 */

@Data
@NoArgsConstructor
public class AdoptionData {
    private Long adoptionId;
    private Long catId; 
    private Long adopterId;
    private LocalDate adoptionDate;
    private String status;

    // Constructor to initialize AdoptionData from an Adoption entity
    public AdoptionData(Adoption adoption) {
        this.adoptionId = adoption.getAdoptionId();
        this.catId = adoption.getCat().getCatId();  // Getting catId from Cat object
        this.adopterId = adoption.getAdopter().getAdopterId();  // Getting adopterId from Adopter object
        this.adoptionDate = adoption.getAdoptionDate();
        this.status = adoption.getStatus();
    }
    
    // Constructor to initialize from catId and adopterId
    public AdoptionData(Long catId, Long adopterId, LocalDate adoptionDate, String status) {
        this.catId = catId;
        this.adopterId = adopterId;
        this.adoptionDate = adoptionDate;
        this.status = status;
    }
}