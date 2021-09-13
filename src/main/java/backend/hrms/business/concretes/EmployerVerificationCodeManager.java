package backend.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.hrms.business.abstracts.EmployerVerificationCodeService;
import backend.hrms.business.abstracts.VerificationService;
import backend.hrms.core.utilities.results.ErrorResult;
import backend.hrms.core.utilities.results.Result;
import backend.hrms.core.utilities.results.SuccessResult;
import backend.hrms.dataAccess.abstracts.EmployerVerificationCodeDao;
import backend.hrms.entities.concretes.Employer;
import backend.hrms.entities.concretes.EmployerVerificationCode;

@Service
public class EmployerVerificationCodeManager implements EmployerVerificationCodeService {
	
	EmployerVerificationCodeDao employerVerificationCodeDao;
	VerificationService verificationService;

	@Autowired
	public EmployerVerificationCodeManager(EmployerVerificationCodeDao employerVerificationCodeDao,
			VerificationService verificationService) {
		
		this.employerVerificationCodeDao = employerVerificationCodeDao;
		this.verificationService = verificationService;
	}

	@Override
	public String createCode() {
		return "XN4EA84";
	}

	@Override
	public Result verifyEmail(String code, int userId) {
		List<EmployerVerificationCode> codes = employerVerificationCodeDao.findByCode(code);
		if (!codes.isEmpty()) {
			for (EmployerVerificationCode employerVerificationCode : codes) {
				if (employerVerificationCode.getEmployerId()==userId) {
					if (employerVerificationCode.isVerified()) return new ErrorResult("this account has already been verified.");
					employerVerificationCode.setVerified(true);
					employerVerificationCode.setVerificationDate(LocalDate.now());
					return new SuccessResult("E-mail is verified.");
				}
			}
		}
		return new ErrorResult("this verification code is invalid.");
	}

	@Override
	public Result add(Employer employer) {

		String code = createCode();
		EmployerVerificationCode employerVerificationCode = new EmployerVerificationCode();
		employerVerificationCode.setCode(code);
		employerVerificationCode.setEmployerId(employer.getId());
		employerVerificationCodeDao.save(employerVerificationCode);
		return verificationService.send(employer.getEmail(), code);
		
	}

}
