package cat.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.clinic.entity.Adoption;

public interface AdoptionDao extends JpaRepository<Adoption, Long> {

}
