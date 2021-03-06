package backend.hrms.business.concretes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.hrms.business.abstracts.CandidateService;
import backend.hrms.business.abstracts.CandidateVerificationCodeService;
import backend.hrms.core.utilities.adapters.MernisServiceAdapter;
import backend.hrms.core.utilities.results.ErrorResult;
import backend.hrms.core.utilities.results.Result;
import backend.hrms.core.utilities.results.SuccessResult;
import backend.hrms.dataAccess.abstracts.CandidateDao;
import backend.hrms.dataAccess.abstracts.UserDao;
import backend.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager extends UserManager implements CandidateService {

	private CandidateDao candidateDao;
	private MernisServiceAdapter mernisServiceAdapter;
	private CandidateVerificationCodeService candidateVerificationCodeService;
	
	@Autowired
	public CandidateManager(UserDao userDao, CandidateDao candidateDao, MernisServiceAdapter mernisServiceAdapter,
			CandidateVerificationCodeService candidateVerificationCodeService) {
		super(userDao);
		this.candidateDao = candidateDao;
		this.mernisServiceAdapter = mernisServiceAdapter;
		this.candidateVerificationCodeService = candidateVerificationCodeService;
	}
	

	@Override
	public List<Candidate> getAll() {
		return this.candidateDao.findAll();
	}

	@Override
	public Result add(Candidate candidate) {
		List<Result> results = new ArrayList<Result>();
		boolean isFail = false;
		
		Result nullControl = nullControlForAdd(candidate);
		Result emailControl = emailControl(candidate);
		Result identityNumberControl = identityNumberControl(candidate);
		Result mernisVerify = verifyWithMernis(candidate);
		Result emailRegexControl = emailRegexControl(candidate);
		
		results.add(nullControl);
		results.add(emailRegexControl);
		results.add(emailControl);
		results.add(identityNumberControl);
		results.add(mernisVerify);
		
		for (var result : results) {
			if (!result.isSuccess()) {
				isFail = true;
				return result;
			}
		}
		
		if (isFail==false) {
			this.candidateDao.save(candidate);
			Result isCodeSaved= candidateVerificationCodeService.add(candidate);
			if (!isCodeSaved.isSuccess()) {
				return new ErrorResult("Candidate added. Verification mail can't send.");
			} else {
				return new SuccessResult("Candidate added. Verification mail sent.");
			}
		} else {
			return new ErrorResult("Candidate can't added.");
		}
	}
	
	public Result nullControlForAdd(Candidate candidate) {
		if (candidate.getIdentityNumber()==""
				|| candidate.getFirstName()==""
				|| candidate.getLastName()==""
				|| candidate.getEmail()==""
				|| candidate.getPassword()==""
				|| candidate.getBirthYear()== 0) 
		{
			return new ErrorResult("Fill the all required fields.");					
		}
		return new SuccessResult();
	}
	
	public Result verifyWithMernis(Candidate candidate) {
		if (!mernisServiceAdapter.verify(candidate)) {
			return new ErrorResult("Identity verification is not succeed!");
		}
		return new SuccessResult();
	}
	
	public Result emailRegexControl(Candidate candidate) {
		String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(candidate.getEmail());
		
		if (!(matcher.find())) {
			return new ErrorResult("Please enter a valid e-mail address.");
		}
		else {
			return new SuccessResult();
		}
	}
	
	public Result identityNumberControl(Candidate candidate) {
		List<Candidate> users = this.candidateDao.findByIdentityNumber(candidate.getIdentityNumber());
		if (!(users.isEmpty())) {
			return new ErrorResult("This identity number is already registered.");
		}
		return new SuccessResult();
	}

}
