package backend.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.hrms.business.abstracts.VerificationService;
import backend.hrms.core.utilities.results.ErrorResult;
import backend.hrms.core.utilities.results.Result;
import backend.hrms.core.utilities.results.SuccessResult;
import backend.hrms.core.utilities.senders.email.EmailSender;

@Service
public class EmailVerificationManager implements VerificationService {
	
	EmailSender emailSender = new EmailSender();
	
	@Autowired
	public EmailVerificationManager() {
		
	}

	@Override
	public Result send(String email, String code) {
		boolean sendEmail = emailSender.sendVerificationCode(email, code);
		if (sendEmail == false) {
			return new ErrorResult();
		} else {
			return new SuccessResult();
		}
	}

}
