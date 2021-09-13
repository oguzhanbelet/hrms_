package backend.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.hrms.business.abstracts.UserService;
import backend.hrms.core.utilities.results.ErrorResult;
import backend.hrms.core.utilities.results.Result;
import backend.hrms.core.utilities.results.SuccessResult;
import backend.hrms.dataAccess.abstracts.UserDao;
import backend.hrms.entities.concretes.User;

@Service
public class UserManager implements UserService {

	UserDao userDao;
	
	
	public UserManager(UserDao userDao) {
		
		this.userDao = userDao;
	}

	@Override
	public Result emailControl(User user) {
		List<User> users = this.userDao.findByEmail(user.getEmail());
		if (!(users.isEmpty())) {
			return new ErrorResult("This e-mail is already registered.");
		}
		return new SuccessResult();
	}	
	

}
