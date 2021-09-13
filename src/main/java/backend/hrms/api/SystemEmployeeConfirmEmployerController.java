package backend.hrms.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import backend.hrms.business.abstracts.SystemEmployeeConfirmEmployerService;
import backend.hrms.core.utilities.results.Result;
import backend.hrms.entities.concretes.SystemEmployeeConfirmEmployer;

public class SystemEmployeeConfirmEmployerController {
	
	private SystemEmployeeConfirmEmployerService systemEmployeeConfirmEmployerService;

	@Autowired
	public SystemEmployeeConfirmEmployerController(
			SystemEmployeeConfirmEmployerService systemEmployeeConfirmEmployerService) {
		
		this.systemEmployeeConfirmEmployerService = systemEmployeeConfirmEmployerService;
	}
	
	@PostMapping("/confirmemployer")
	public Result confirm(@RequestBody SystemEmployeeConfirmEmployer systemEmployeeConfirmEmployer) {
		return this.systemEmployeeConfirmEmployerService.confirmEmployer(systemEmployeeConfirmEmployer);
	}

}
