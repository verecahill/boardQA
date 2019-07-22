package app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import app.dto.Board;
import app.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Override
	public Page<Board> getBoardWithPaginated(int page, int size) {
		Pageable pageable = new PageRequest(page, size, new Sort(Direction.DESC, "createDate"));
		Page<Board> boardPage = boardRepository.findAll(pageable);
//		System.out.println(boardPage.getTotalElements());
//		System.out.println(boardPage.getTotalPages());
		
		return boardPage;
	}

}
