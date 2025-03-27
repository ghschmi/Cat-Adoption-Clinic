package cat.clinic.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cat.clinic.controller.model.AdoptionData;
import cat.clinic.service.AdoptionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cat_clinic")
@Slf4j
public class AdoptionController {
	
	@Autowired
	private AdoptionService adoptionService;
	
	@PostMapping("/adoption")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AdoptionData insertAdoption(
			@RequestBody AdoptionData adoptionData) {
		log.info("Creating adoption request {}", adoptionData);
		return adoptionService.saveAdoption(adoptionData);
	}
	
	@PutMapping("/adoption/{adoptionId}")
	public AdoptionData updateAdoption(@PathVariable Long adoptionId, @RequestBody AdoptionData adoptionData) {
		adoptionData.setAdoptionId(adoptionId);
		log.info("Updating adoption {}", adoptionData);
		return adoptionService.saveAdoption(adoptionData);
	}
	
	@GetMapping("/adoption")
	public List<AdoptionData> retrieveAllAdoptions() {
		log.info("Retrieve all adoptions called.");
		return adoptionService.retrieveAllAdoptions();
	}
	
	@GetMapping("/adoption/{adoptionId}")
	public AdoptionData retrieveAdoptionById(@PathVariable Long adoptionId) {
		log.info("Retrieving adoption with ID={}", adoptionId);
		return adoptionService.retrieveAdoptionById(adoptionId);
	}
	
	@DeleteMapping("/adoption")
	public void deleteAllAdoptions() {
		log.info("Attempting to delete all adoptions");
		throw new UnsupportedOperationException("Deleting all adoptions is not allowed");
	}
	
	@DeleteMapping("/adoption/{adoptionId}")
	public Map<String, String> deleteAdoptionById(@PathVariable Long adoptionId) {
		log.info("Deleting adoption with ID={}", adoptionId);
		
		adoptionService.deleteAdoptionById(adoptionId);
		
		return Map.of("message", "Deletion of adoption with ID=" + adoptionId + " was successful.");
	}
	
	
	
	
}
