package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.repository.BoardRepository;

@Controller
public class HomeController {

	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("boards", boardRepository.findAll());
		return "index";
	}
}
