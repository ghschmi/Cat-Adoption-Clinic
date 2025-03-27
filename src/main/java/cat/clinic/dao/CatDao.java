package cat.clinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.clinic.entity.Cat;

public interface CatDao extends JpaRepository<Cat, Long> {

}
