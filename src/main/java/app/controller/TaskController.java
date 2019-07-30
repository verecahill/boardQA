package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.dto.Task;
import app.dto.Board;
import app.dto.Account;
import app.repository.TaskRepository;
import app.repository.BoardRepository;
import app.util.HttpSessionUtils;

@Controller
@RequestMapping("/boards/{boardId}/tasks")
public class TaskController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping("")
	public String create(@PathVariable Long boardId, String contents, String title, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "user/login";
		}
		
		Account loginUser = HttpSessionUtils.getUserFromSession(session);
		Board question = boardRepository.findOne(boardId);
		Task answer = new Task(loginUser, question, contents, title);
		taskRepository.save(answer);
		return String.format("redirect:/questions/%d", boardId);
		
	}
}
