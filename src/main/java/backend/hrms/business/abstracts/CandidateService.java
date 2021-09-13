package backend.hrms.business.abstracts;

import java.util.List;

import backend.hrms.core.utilities.results.Result;
import backend.hrms.entities.concretes.Candidate;

public interface CandidateService {
	
	List<Candidate> getAll();
	Result add(Candidate candidate);

}
