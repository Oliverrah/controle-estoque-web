package com.made4you.controle.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.made4you.controle.web.entities.Provider;
import com.made4you.controle.web.entities.User;
import com.made4you.controle.web.service.ProviderService;
import com.made4you.controle.web.service.UserService;

@Controller
public class ProviderController {

	@Autowired
	ProviderService providerService;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/showProviderForm")
	public String showProviderForm(Model model) {
		
		model.addAttribute("provider", new Provider());
				
		return "provider-registration";
	}
	
	@RequestMapping("/showProviderFormForUpdate")
	public String showProviderFormForUpdate(@RequestParam("providerId") Long providerId, Model model) {
		
		Provider provider = providerService.findById(providerId);
		
		model.addAttribute("provider", provider);
		
		return "provider-registration";
	}
	
	@RequestMapping("/addProvider")
	public String addProduct(@Valid @ModelAttribute Provider provider, BindingResult bindingResult, @RequestParam("userId") int userId, Model model) {
		
		User user = userService.findById(userId);
		user.add(provider);
		
		Provider providerExists = providerService.findByCnpj(provider.getCnpj(), userId);
				
		if(bindingResult.hasErrors()) {
			return "provider-registration";
		}
				
		if(providerExists == null) {
			providerService.save(provider);
			return "redirect:/providers";
		}
		else if(providerExists.getId() != provider.getId()) {
			model.addAttribute("registrationError", "Já existe fornecedor com esse CNPJ");
			return "provider-registration";
		}
		else {		
			providerService.save(provider);
			return "redirect:/providers";
		}
	}
	
	@RequestMapping("/deleteProvider")
	public String deleteProvider(@RequestParam("providerId") Long providerId) {
		
		Provider provider = providerService.findById(providerId);
		
		providerService.delete(provider);
		
		return "redirect:/providers";
	}
	
	@RequestMapping("/providers")
	public String showCategories(Model model, HttpServletRequest request) {
		HttpSession currentSession = request.getSession();
		
		User user = (User) currentSession.getAttribute("user");	
		int userId = user.getId();
					
		List<Provider> providers = providerService.findAll(userId);
		model.addAttribute("providers", providers);
		
		return "providers";
	}
	
	@RequestMapping("/searchProvider")
	public String searchProvider(Model model, @RequestParam String providerName, HttpServletRequest request) {
		HttpSession currentSession = request.getSession();
		
		User user = (User) currentSession.getAttribute("user");
		
		int userId = user.getId();
		
		List<Provider> providers = providerService.searchProviders(providerName, userId);
		
		model.addAttribute("providers", providers);
		model.addAttribute("providerName", providerName);		
		
		return "providers";
	}
}
