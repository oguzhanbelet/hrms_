package backend.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.hrms.entities.concretes.SystemEmployeeConfirmEmployer;

public interface SystemEmployeeConfirmEmployerDao extends JpaRepository<SystemEmployeeConfirmEmployer, Integer> {

}
