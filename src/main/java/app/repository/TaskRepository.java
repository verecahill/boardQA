package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.dto.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
}
