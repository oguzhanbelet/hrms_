package backend.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.hrms.entities.concretes.SystemEmployeeConfirm;

public interface SystemEmployeeConfirmDao extends JpaRepository<SystemEmployeeConfirm, Integer> {

}
