package backend.hrms.business.abstracts;

import backend.hrms.core.utilities.results.Result;
import backend.hrms.entities.concretes.Employer;

public interface EmployerVerificationCodeService extends VerificationCodeService {
	
	Result add(Employer employer);

}
