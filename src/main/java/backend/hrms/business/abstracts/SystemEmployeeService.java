package backend.hrms.business.abstracts;

import java.util.List;

import backend.hrms.core.utilities.results.Result;
import backend.hrms.entities.concretes.SystemEmployee;

public interface SystemEmployeeService {
	
	List<SystemEmployee> getAll();
	Result add(SystemEmployee systemEmployee);

}
