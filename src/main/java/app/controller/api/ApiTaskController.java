package app.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.dto.Task;
import app.dto.Board;
import app.dto.User;
import app.repository.TaskRepository;
import app.repository.BoardRepository;
import app.util.HttpSessionUtils;
import app.util.Result;

@RestController
@RequestMapping("/api/boards/{boardId}/tasks")
public class ApiTaskController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@PostMapping("")
	public Task create(@PathVariable Long boardId, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Board board = boardRepository.findOne(boardId);
		Task task = new Task(loginUser, board, contents);
		board.addAnswer();
		return taskRepository.save(task);
		
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long boardId, @PathVariable Long id, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인해야 합니다.");
		}
		
		Task task = taskRepository.findOne(id);
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!task.isSameWriter(loginUser)) {
			return Result.fail("자신의 글만 삭제 할 수 있습니다.");
		}
		
		taskRepository.delete(id);
		Board board = boardRepository.findOne(boardId);
		board.deleteAnswer();
		boardRepository.save(board);
		return Result.ok();
	}
}
