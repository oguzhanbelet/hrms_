package backend.hrms.business.abstracts;

import backend.hrms.core.utilities.results.Result;

public interface VerificationCodeService {
	String createCode();

	Result verifyEmail(String code, int userId);
}
