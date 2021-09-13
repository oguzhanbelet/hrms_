package backend.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.hrms.entities.concretes.City;

public interface CityDao extends JpaRepository<City, Integer> {

}
