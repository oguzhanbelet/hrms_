package backend.hrms.core.utilities.adapters;

import org.springframework.stereotype.Service;

import backend.hrms.MernisTest.MernisVerification;
import backend.hrms.entities.concretes.User;

@Service
public class MernisServiceAdapter {
	
	public boolean verify(User user) {
		MernisVerification adapter = new MernisVerification();
		return adapter.verify(user);
	}

}
