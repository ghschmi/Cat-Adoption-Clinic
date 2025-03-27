package cat.clinic.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.clinic.controller.model.AdoptionData;
import cat.clinic.dao.AdopterDao;
import cat.clinic.dao.AdoptionDao;
import cat.clinic.dao.CatDao;
import cat.clinic.entity.Adopter;
import cat.clinic.entity.Adoption;
import cat.clinic.entity.Cat;

@Service
/*
 * public class AdoptionService {
 * 
 * @Autowired private AdoptionDao adoptionDao;
 * 
 * @Autowired private CatDao catDao;
 * 
 * @Autowired private AdopterDao adopterDao;
 * 
 * @Transactional(readOnly = false) public AdoptionData
 * saveAdoption(AdoptionData adoptionData) { Long adoptionId =
 * adoptionData.getAdoptionId(); Adoption adoption =
 * findOrCreateAdoption(adoptionId);
 * 
 * setFieldsInAdoption(adoption, adoptionData); return new
 * AdoptionData(adoptionDao.save(adoption)); }
 * 
 * private void setFieldsInAdoption(Adoption adoption, AdoptionData
 * adoptionData) {
 * 
 * Cat cat = adoptionData.getCat(); if
 * (!"available".equals(cat.getAdoptionStatus())) { throw new
 * IllegalStateException("Cat is not available for adoption."); }
 * 
 * Adopter adopter = adoptionData.getAdopter(); if (adopter == null ||
 * adopter.getAdopterId() == null) { throw new
 * IllegalArgumentException("Invalid adopter."); }
 * 
 * adoption.setCat(cat); adoption.setAdopter(adopter);
 * adoption.setAdoptionDate(adoptionData.getAdoptionDate());
 * adoption.setStatus(adoptionData.getStatus()); }
 * 
 * private Adoption findOrCreateAdoption(Long adoptionId) { Adoption adoption;
 * 
 * if(Objects.isNull(adoptionId)) { adoption = new Adoption(); } else { adoption
 * = findAdoptionById(adoptionId); } return adoption; }
 * 
 * private Adoption findAdoptionById(Long adoptionId) { return
 * adoptionDao.findById(adoptionId).orElseThrow(() -> new
 * NoSuchElementException("Adoption with ID=" + adoptionId +
 * " was not found.")); } }
 */

public class AdoptionService {
    
    @Autowired
    private AdoptionDao adoptionDao;
    
    @Autowired
    private CatDao catDao;
    
    @Autowired
    private AdopterDao adopterDao;
    
    @Transactional(readOnly = false)
    public AdoptionData saveAdoption(AdoptionData adoptionData) {
        Long adoptionId = adoptionData.getAdoptionId();
        Adoption adoption = findOrCreateAdoption(adoptionId);

        // Fetch the Cat and Adopter using the IDs provided
        Cat cat = fetchCatById(adoptionData.getCatId());
        Adopter adopter = fetchAdopterById(adoptionData.getAdopterId());

        // Set the fields in the Adoption entity
        setFieldsInAdoption(adoption, cat, adopter, adoptionData);
        
        return new AdoptionData(adoptionDao.save(adoption)); // Save the adoption record
    }

    private Cat fetchCatById(Long catId) {
        if (catId == null) {
            throw new IllegalArgumentException("Invalid cat ID.");
        }
        return catDao.findById(catId).orElseThrow(() -> 
            new NoSuchElementException("Cat with ID=" + catId + " not found."));
    }
    
    private Adopter fetchAdopterById(Long adopterId) {
        if (adopterId == null) {
            throw new IllegalArgumentException("Invalid adopter ID.");
        }
        return adopterDao.findById(adopterId).orElseThrow(() -> 
            new NoSuchElementException("Adopter with ID=" + adopterId + " not found."));
    }

    private void setFieldsInAdoption(Adoption adoption, Cat cat, Adopter adopter, AdoptionData adoptionData) {
    	 if (cat == null || !"available".equalsIgnoreCase(cat.getAdoptionStatus())) {
    	        throw new IllegalStateException("Cat is not available for adoption.");
        }
        adoption.setCat(cat);
        adoption.setAdopter(adopter);
        adoption.setAdoptionDate(adoptionData.getAdoptionDate());
        adoption.setStatus(adoptionData.getStatus());
    }

    private Adoption findOrCreateAdoption(Long adoptionId) {
        if (adoptionId == null) {
            return new Adoption(); // Create new adoption if no ID provided
        }
        return findAdoptionById(adoptionId); // Find existing adoption
    }

    private Adoption findAdoptionById(Long adoptionId) {
        return adoptionDao.findById(adoptionId).orElseThrow(() -> 
            new NoSuchElementException("Adoption with ID=" + adoptionId + " not found."));
    }
    
    @Transactional(readOnly = true)
	public List<AdoptionData> retrieveAllAdoptions() {
		List<Adoption> adoptions = adoptionDao.findAll();
		List<AdoptionData> response = new LinkedList<>();
		
		for(Adoption adoption : adoptions) {
			response.add(new AdoptionData(adoption));
		}
		
		return response;
	}
    
    @Transactional(readOnly = true)
    public AdoptionData retrieveAdoptionById(Long adoptionId) {
    	Adoption adoption = findAdoptionById(adoptionId);
    	return new AdoptionData(adoption);
    	
    }
    
    @Transactional(readOnly = false)
	public void deleteAdoptionById(Long adoptionId) {
		Adoption adoption = findAdoptionById(adoptionId);
		adoptionDao.delete(adoption);
	}
}