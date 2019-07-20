package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.dto.User;
import app.repository.UserRepository;
import app.util.HttpSessionUtils;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("")
	public String create(User user) {
//		System.out.println(user);
//		users.add(user);
		userRepository.save(user);
		return "redirect:users";
	}
	
	@GetMapping("")
	public String list(Model model) {
//		model.addAttribute("users",users);
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("login")
	public String login(String userId, String password, HttpSession session) {
		
		User user = userRepository.findByUserId(userId);
		
		if (user == null) {
			System.out.println("not matched userId");
			return "redirect:/users/login";
		}
		if (!user.matchPassword(password)) {
			System.out.println("wrong password");
			return "redirect:/users/login";
		}
		
		System.out.println("Login success");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		
		return "redirect:/";
	}
	
	
	@GetMapping("form")
	public String form() {
	
		return "/user/form";
	}
	
	@GetMapping("{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session){
		
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!sessionedUser.matchId(id)){
			throw new IllegalStateException("illegal access");
		}
		
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "/user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updateUser, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!id.equals(sessionedUser.getId())){
			throw new IllegalStateException("illegal access");
		}
		
		User user = userRepository.findOne(id);
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/users";
	}
}
