package app.controller;

import java.util.ArrayList;
import java.util.List;

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

@Controller
@RequestMapping("/users")
public class UserController {
	
	private List<User> users = new ArrayList<User>();
	
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
	
	@GetMapping("form")
	public String form() {
	
		return "/user/form";
	}
	
	@GetMapping("{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		return "/user/updateForm";
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) {
		User user = userRepository.findOne(id);
		user.update(newUser);
		userRepository.save(user);
		return "redirect:/users";
	}
}
