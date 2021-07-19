package com.made4you.controle.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.made4you.controle.web.entity.User;
import com.made4you.controle.web.service.UserService;
import com.made4you.controle.web.user.CtrlUser;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
			
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/")
	public String showHome() {
		return "dashboard";
	}

	@RequestMapping("/login")
	public String showLogin() {		
		return "login";
	}
	

	@GetMapping("/showUserForm")
	public String showForm(Model model) {
		
		model.addAttribute("ctrlUser", new CtrlUser());
		
		return "user-registration";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("ctrlUser") CtrlUser ctrlUser,
											BindingResult bindingResult,
											Model model) {
							
		String userName = ctrlUser.getUsername();
					
		if(bindingResult.hasErrors()) {
			return "user-registration";
		}
				
		User userExists = userService.findByUserName(userName);
		if(userExists != null) {
			model.addAttribute("ctrlUser", new CtrlUser());
			model.addAttribute("registrationError", "Já existe um usuário com esse nome.");
			return "user-registration";
		}
		
		userService.save(ctrlUser);
		
		return "login";
	}
	
	
	@RequestMapping("/dashboard")
	public String showDashboardPage() {
		return "dashboard";
	}
	
}
