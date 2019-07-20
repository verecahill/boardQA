package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.dto.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
	
}
