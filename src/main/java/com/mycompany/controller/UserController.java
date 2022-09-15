package com.mycompany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycompany.entities.User;
import com.mycompany.exceptions.UserNotFoundException;
import com.mycompany.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public String getAllUser(Model model) {
		List<User> listUsers = this.userService.findAll();
		model.addAttribute("listUsers",listUsers);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String showNewForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("pageTitle","Add New User");
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes ra) {
		this.userService.save(user);
		ra.addFlashAttribute("message", "The user has been saved successfully.");
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String showEditForm(@PathVariable("id") Integer id, Model model,RedirectAttributes ra) {
		try {
			User user = this.userService.findByUserId(id);
			model.addAttribute("user", user);
			model.addAttribute("pageTitle","Edit User (ID: " + id + ")");
			return "user_form";
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
			return "redirect:/users";
		}
	}
	
	
	@GetMapping("/users/delete/{id}")
	public String deleteUser(@PathVariable("id") Integer id,RedirectAttributes ra) {
		try {
			this.userService.deleteUser(id);
		
		
		} catch (UserNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
	
		}
		return "redirect:/users";
	}
	
	
	
	
}
