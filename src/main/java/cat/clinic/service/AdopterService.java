package cat.clinic.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.clinic.controller.model.AdopterData;
import cat.clinic.dao.AdopterDao;
import cat.clinic.entity.Adopter;

@Service
public class AdopterService {
	
	@Autowired
	private AdopterDao adopterDao;

	@Transactional(readOnly = false)
	public AdopterData saveAdopter(AdopterData adopterData) {
		Long adopterId = adopterData.getAdopterId();
		Adopter adopter = findOrCreateAdopter(adopterId);
		
		setFieldsInAdopter(adopter, adopterData);
		return new AdopterData(adopterDao.save(adopter));
	}

	private void setFieldsInAdopter(Adopter adopter, AdopterData adopterData) {
		adopter.setAdopterEmail(adopterData.getAdopterEmail());
		adopter.setAdopterName(adopterData.getAdopterName());
		adopter.setAdoptionHistory(adopterData.getAdoptionHistory());
	}

	private Adopter findOrCreateAdopter(Long adopterId) {
		Adopter adopter;
		
		if(Objects.isNull(adopterId)) {
			adopter = new Adopter();
		}
		else {
			adopter = findAdopterById(adopterId);
		}
		
		return adopter;
	}

	private Adopter findAdopterById(Long adopterId) {
		return adopterDao.findById(adopterId).orElseThrow(() -> new NoSuchElementException("Adopter with ID=" + adopterId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<AdopterData> retrieveAllAdopters() { //method to get all adopters
		List<Adopter> adopters = adopterDao.findAll();
		List<AdopterData> response = new LinkedList<>();
		
		for(Adopter adopter : adopters) {
			response.add(new AdopterData(adopter));
		}
		
		return response;
	}

	@Transactional(readOnly = true)
	public AdopterData retrieveAdopterById(Long adopterId) {
		Adopter adopter = findAdopterById(adopterId);
		return new AdopterData(adopter);
		
	}
	@Transactional(readOnly = false)
	public void deleteAdopterById(Long adopterId) {
		Adopter adopter = findAdopterById(adopterId);
		adopterDao.delete(adopter);
	}
}
