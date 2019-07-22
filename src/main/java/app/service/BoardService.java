package app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import app.dto.Board;

public interface BoardService {
	public Page<Board> getBoardWithPaginated(int page, int size);
}
