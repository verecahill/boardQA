package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import app.dto.Board;
import app.dto.User;
import app.repository.BoardRepository;
import app.util.HttpSessionUtils;

@Controller
@RequestMapping("/boards")
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "user/login";
		}
		return "qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "user/login";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		Board newBoard = new Board(sessionUser, title, contents);
		boardRepository.save(newBoard);
		
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		model.addAttribute("board", boardRepository.findOne(id));
		return "/qna/show";
				
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		try {
			Board board = boardRepository.findOne(id);
			hasPermission(session, board);
			model.addAttribute("board", board);
			return "/qna/updateForm";
			
		} catch (IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
	}
	
	private boolean hasPermission(HttpSession session, Board board) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("로그인이 필요합니다.");
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if(!board.isSameWriter(loginUser)) {
			throw new IllegalStateException("자신의 글만 수정, 삭제가 가능합니다.");
		}
		
		return true;
	}
	
	@PutMapping("/{id}")
	public String udpate(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
		try {
			Board board = boardRepository.findOne(id);
			hasPermission(session, board);
			board.update(title, contents);
			boardRepository.save(board);
			return "redirect:/questions/" + id.toString();
			
		} catch (IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
		
		
		
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, Model model, HttpSession session) {
		try {
			System.out.println("delete board");
			Board board = boardRepository.findOne(id);
			System.out.println(board.toString());
			hasPermission(session, board);
			System.out.println("has permission");
			boardRepository.delete(id);
			System.out.println("delete success");
			return "redirect:/";
			
		} catch (IllegalStateException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "user/login";
		}
	}
	
}
