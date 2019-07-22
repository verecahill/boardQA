package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.dto.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
