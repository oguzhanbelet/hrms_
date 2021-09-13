package backend.hrms.business.abstracts;

import backend.hrms.core.utilities.results.Result;
import backend.hrms.entities.concretes.Candidate;

public interface CandidateVerificationCodeService extends VerificationCodeService{
	Result add(Candidate candidate);
}
