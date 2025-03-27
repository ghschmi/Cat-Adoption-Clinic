package cat.clinic.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.clinic.controller.model.CatData;
import cat.clinic.dao.CatDao;
import cat.clinic.entity.Cat;

@Service
public class CatService {
	
	@Autowired
	private CatDao catDao;
	
	@Transactional(readOnly = false)
	public CatData saveCat(CatData catData) {
		Long catId = catData.getCatId();
		Cat cat = findOrCreateCat(catId);
		
		setFieldsInCat(cat, catData);
		return new CatData(catDao.save(cat));
			
		}
		
	private void setFieldsInCat(Cat cat, CatData catData) {
		cat.setCatName(catData.getCatName());
		cat.setAge(catData.getAge());
		cat.setBreed(catData.getBreed());
		cat.setHealthStatus(catData.getHealthStatus());
		cat.setDescription(catData.getDescription());
		cat.setAdoptionStatus(catData.getAdoptionStatus());
	}
		

	private Cat findOrCreateCat(Long catId) {
		Cat cat;
		
		if(Objects.isNull(catId)) {
			cat = new Cat();
		}
		else {
			cat = findCatById(catId);
		}
		return cat;
	}

	private Cat findCatById(Long catId) {
		return catDao.findById(catId).orElseThrow(() -> new NoSuchElementException("Cat with ID=" + catId + " was not found."));
	}

	@Transactional(readOnly = true)
	public List<CatData> retrieveAllCats() {
		List<Cat> cats = catDao.findAll();
		List<CatData> response = new LinkedList<>();
		
		for(Cat cat : cats) {
			response.add(new CatData(cat));
		}
		
		return response;
	}
	
	@Transactional(readOnly = true)
	public CatData retrieveCatByID(Long catId) {
		Cat cat = findCatById(catId);
		return new CatData(cat);
	}

	@Transactional(readOnly = false)
	public void deleteCatById(Long catId) {
		Cat cat = findCatById(catId);
		catDao.delete(cat);
		
	}
}
