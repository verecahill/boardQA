package app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import app.dto.Account;
import app.repository.UserRepository;
import app.service.AccountService;
import app.service.SecurityService;
import app.util.HttpSessionUtils;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	AccountService userService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/registration")
	public String create(@ModelAttribute("user") Account user, String[] roles) {
		System.out.println("user signup");
		String password = user.getPassword();
		userService.saveUser(user, roles);
		System.out.println("user : " + user);
		securityService.autologin(user.getUserId(), password);
		return "redirect:/";
	}
	
	@GetMapping("")
	public String list(Model model) {
//		model.addAttribute("users",users);
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("/login")
	public String loginForm(String error, Model model) {
		System.out.println("Login form");
		if(error != null) {
			model.addAttribute("errorMessage", "UserId or Password is wrong");
		}
		return "user/login";
	}
	
	
//	@PostMapping("login")
//	public String login(String userId, String password, HttpSession session) {
	public String login(String userId, String password, Model model, HttpSession session) {
		
		System.out.println("login start");
		Account user = userRepository.findByUserId(userId);
		System.out.println("found user id");
		if (user == null) {
			System.out.println("not matched userId");
			model.addAttribute("errorMessage", "not matched userId");
			return "user/login";
		}
		if (!user.matchPassword(password)) {
			System.out.println("wrong password");
			model.addAttribute("errorMessage", "wrong password");
			return "user/login";
		}
		System.out.println("found user data");				
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		System.out.println("Login success");
		
		return "redirect:/";
	}
	

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		
		return "redirect:/";
	}
	
	
	@GetMapping("form")
	public String form() {
	
		return "user/form";
	}
	
	@GetMapping("{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session){
		
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		
		Account sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!sessionedUser.matchId(id)){
			throw new IllegalStateException("illegal access");
		}
		
		Account user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "user/updateForm";
	}
	
	@PutMapping("{id}")
	public String update(@PathVariable Long id, Account updateUser, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/login";
		}
		
		Account sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!id.equals(sessionedUser.getId())){
			throw new IllegalStateException("illegal access");
		}
		
		Account user = userRepository.findOne(id);
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/";
	}
	
	@GetMapping("/profile/{id}")
	public String showProfile(@PathVariable Long id, Model model) {
		Account user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "user/profile";
	}
}
