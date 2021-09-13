package backend.hrms.business.concretes;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.hrms.business.abstracts.SystemEmployeeConfirmEmployerService;
import backend.hrms.core.utilities.results.Result;
import backend.hrms.core.utilities.results.SuccessResult;
import backend.hrms.dataAccess.abstracts.SystemEmployeeConfirmEmployerDao;
import backend.hrms.entities.concretes.SystemEmployeeConfirmEmployer;

@Service
public class SystemEmployeeConfirmEmployerManager implements SystemEmployeeConfirmEmployerService {
	
	SystemEmployeeConfirmEmployerDao systemEmployeeConfirmEmployerDao;

	@Autowired
	public SystemEmployeeConfirmEmployerManager(SystemEmployeeConfirmEmployerDao systemEmployeeConfirmEmployerDao) {
		
		this.systemEmployeeConfirmEmployerDao = systemEmployeeConfirmEmployerDao;
	}

	@Override
	public Result confirmEmployer(SystemEmployeeConfirmEmployer systemEmployeeConfirmEmployer) {

		systemEmployeeConfirmEmployer.setConfirmDate(Date.valueOf(LocalDate.now()));
		systemEmployeeConfirmEmployer.setConfirmed(true);
		systemEmployeeConfirmEmployerDao.save(systemEmployeeConfirmEmployer);
		
		return new SuccessResult("Employer is confirmed.");
		
	}

}
