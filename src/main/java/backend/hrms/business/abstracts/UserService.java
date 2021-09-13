package backend.hrms.business.abstracts;

import backend.hrms.core.utilities.results.Result;
import backend.hrms.entities.concretes.User;

public interface UserService {
	
	Result emailControl(User user);

}
