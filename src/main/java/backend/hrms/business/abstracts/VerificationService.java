package backend.hrms.business.abstracts;

import backend.hrms.core.utilities.results.Result;

public interface VerificationService {
	
	Result send(String email, String code);

}
