package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	
	@GetMapping("/hello")
	public String helloPage(Authentication authentication, @RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "4") int size, Model model){
		
//		UserDetails user = (UserDetails) authentication.getPrincipal();
//		session.setAttribute("sessionedUser", user);
		Page<Board> boards = boardService.getBoardWithPaginated(page, size);
		model.addAttribute("boards", boards.getContent());
		model.addAttribute("page", page+1);
		return "hello";
	}
	
	
}
