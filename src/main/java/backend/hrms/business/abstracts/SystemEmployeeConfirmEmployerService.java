package backend.hrms.business.abstracts;

import backend.hrms.core.utilities.results.Result;
import backend.hrms.entities.concretes.SystemEmployeeConfirmEmployer;

public interface SystemEmployeeConfirmEmployerService {
	
	Result confirmEmployer(SystemEmployeeConfirmEmployer systemEmployeeConfirmEmployer);

}
