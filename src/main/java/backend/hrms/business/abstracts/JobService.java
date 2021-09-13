package backend.hrms.business.abstracts;

import java.util.List;

import backend.hrms.core.utilities.results.Result;
import backend.hrms.entities.concretes.Job;

public interface JobService {
	
	List<Job> getAll();
	Result add(Job job);

}
