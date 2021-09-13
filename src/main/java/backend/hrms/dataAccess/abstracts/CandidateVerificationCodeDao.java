package backend.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import backend.hrms.entities.concretes.CandidateVerificationCode;

public interface CandidateVerificationCodeDao extends JpaRepository<CandidateVerificationCode, Integer> {
	
	List<CandidateVerificationCode> findByCode(String code);

}
