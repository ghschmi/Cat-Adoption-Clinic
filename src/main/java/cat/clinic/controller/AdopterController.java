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

import cat.clinic.controller.model.AdopterData;
import cat.clinic.service.AdopterService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cat_clinic")
@Slf4j
public class AdopterController {
	@Autowired
	private AdopterService adopterService;

	@PostMapping("/adopter")
	@ResponseStatus(code = HttpStatus.CREATED)
	public AdopterData insertAdopter(@RequestBody AdopterData adopterData) {
		log.info("Creating adopter {}", adopterData);
		return adopterService.saveAdopter(adopterData);
	}

	@PutMapping("/adopter/{adopterId}")
	public AdopterData updateAdopter(@PathVariable Long adopterId, @RequestBody AdopterData adopterData) {
		adopterData.setAdopterId(adopterId);
		log.info("Updating adopter {}", adopterData);
		return adopterService.saveAdopter(adopterData);

	}

	@GetMapping("/adopter")
	public List<AdopterData> retrieveAllAdopters() {
		log.info("Retrieve all adopters called.");
		return adopterService.retrieveAllAdopters();
	}

	@GetMapping("/adopter/{adopterId}")
	public AdopterData retrieveAdopterById(@PathVariable Long adopterId) {
		log.info("Retrieving adopter with ID={}", adopterId);
		return adopterService.retrieveAdopterById(adopterId);
	}

	@DeleteMapping("/adopter")
	public void deleteAllAdopters() {
		log.info("Attempting to delete all adopters");
		throw new UnsupportedOperationException("Deleting all adopters is not allowed");
	}

	@DeleteMapping("/adopter/{adopterId}")
	public Map<String, String> deleteAdopterById(@PathVariable Long adopterId) {
		log.info("Deleting adopter with ID={}", adopterId);
		
		adopterService.deleteAdopterById(adopterId);
		
		return Map.of("message", "Deletion of adopter with ID=" + adopterId + " was successful.");
	}

}
