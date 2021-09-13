package backend.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.hrms.business.abstracts.EmployerService;
import backend.hrms.business.abstracts.EmployerVerificationCodeService;
import backend.hrms.core.utilities.results.ErrorResult;
import backend.hrms.core.utilities.results.Result;
import backend.hrms.core.utilities.results.SuccessResult;
import backend.hrms.dataAccess.abstracts.EmployerDao;
import backend.hrms.dataAccess.abstracts.UserDao;
import backend.hrms.entities.concretes.Employer;

@Service
public class EmployerManager extends UserManager implements EmployerService {
	
	EmployerDao employerDao;
	EmployerVerificationCodeService employerVerificationCodeService;
	
	
	@Autowired
	public EmployerManager(UserDao userDao, EmployerDao employerDao,
			EmployerVerificationCodeService employerVerificationCodeService) {
		super(userDao);
		this.employerDao = employerDao;
		this.employerVerificationCodeService = employerVerificationCodeService;
	}

	@Override
	public List<Employer> getAll() {
		return this.employerDao.findAll();
	}

	@Override
	public Result add(Employer employer) {
		List<Result> results = new ArrayList<Result>();
		boolean isFail = false;
		
		Result nullControl = nullControlForAdd(employer);
		Result emailControl = emailControl(employer);
		Result employerEmailControl = employerEmailControl(employer);
		
		results.add(nullControl);
		results.add(emailControl);
		results.add(employerEmailControl);
		
		for (var result : results) {
			if (!result.isSuccess()) {
				isFail = true;
				return result;
			}
		}
		
		if (isFail==false) {
			this.employerDao.save(employer);
			Result isCodeSaved = employerVerificationCodeService.add(employer);
			if (!isCodeSaved.isSuccess()) {
				return new ErrorResult("Employer added. Verification mail can't send");
			} else {
				return new SuccessResult("Employer added. Verification mail send");
			}
		} else {
			return new ErrorResult();
		}
	}
	
	private Result nullControlForAdd(Employer employer) {
		if (employer.getCompanyName()==""
				|| employer.getWebAdress()==""
				|| employer.getPhoneNumber()==""
				|| employer.getEmail()==""
				|| employer.getPassword() =="") 
		{
			return new ErrorResult("fill the all requiered fields.");			
		}
		
		return new SuccessResult();
	}
	
	public Result employerEmailControl(Employer employer) {
		String[] emailSplit = employer.getEmail().split("@");
		if (!employer.getWebAdress().contains(emailSplit[1])) {
			return new ErrorResult("The e-mail address must be an extension of the web address. For example: name@YourDomainName.com");
		} else {
			return new SuccessResult();
		}
	}

}
