package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.dto.Board;
import app.repository.BoardRepository;
import app.service.BoardService;

@Controller
public class HomeController {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private BoardService boardService;
//	
//	@GetMapping("")
//	public String home(Model model) {
//		model.addAttribute("boards", boardRepository.findAll());
//		return "index";
//	}

	@GetMapping("")
	public String boardPages(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "4") int size, Model model) {
		
		Page<Board> boards = boardService.getBoardWithPaginated(page, size);
		model.addAttribute("boards", boards.getContent());
		model.addAttribute("page", page+1);
		return "index";
	}
}
