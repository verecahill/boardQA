package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.dto.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
