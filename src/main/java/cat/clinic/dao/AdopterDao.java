package cat.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.clinic.entity.Adopter;

public interface AdopterDao extends JpaRepository<Adopter, Long> {

}
