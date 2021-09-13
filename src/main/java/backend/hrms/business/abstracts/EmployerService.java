package backend.hrms.business.abstracts;

import java.util.List;

import backend.hrms.core.utilities.results.Result;
import backend.hrms.entities.concretes.Employer;

public interface EmployerService {
	
	List<Employer> getAll();
	Result add(Employer employer);

}
