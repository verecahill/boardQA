package app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import app.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	Page<Board> findAll(Pageable request);
}
