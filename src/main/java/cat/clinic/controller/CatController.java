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

import cat.clinic.controller.model.CatData;
import cat.clinic.service.CatService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cat_clinic")
@Slf4j
public class CatController {
	@Autowired
	private CatService catService;
	
	@PostMapping("/cat")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CatData insertCat(
			@RequestBody CatData catData) {	
		log.info("Creating cat {}", catData);
		return catService.saveCat(catData);
	}
	
	@PutMapping("/cat/{catId}")
	public CatData updateCat(@PathVariable Long catId, @RequestBody CatData catData) {
		catData.setCatId(catId);
		log.info("Updating cat {}", catData);
		return catService.saveCat(catData);
	}
	
	@GetMapping("/cat")
	public List<CatData> retrieveAllCats() {
		log.info("Retrieve all cats called.");
		return catService.retrieveAllCats();
	}
	
	@GetMapping("/cat/{catId}")
	public CatData retrieveCatById(@PathVariable Long catId) {
		log.info("Retrieving cat with ID={}", catId);
		return catService.retrieveCatByID(catId);
	}
	
	@DeleteMapping("/cat")
	public void deleteAllCats() {
		log.info("Attempting to delete all cats");
		throw new UnsupportedOperationException("Deleting all cats is not allowed");
	}
	
	@DeleteMapping("/cat/{catId}")
	public Map<String, String> deleteCatById(@PathVariable Long catId) {
		log.info("Deleting cat with ID={}", catId);
		
		catService.deleteCatById(catId);
		
		return Map.of("message", "Deletion of cat with ID=" + catId + " was successful.");
	}
	
}
