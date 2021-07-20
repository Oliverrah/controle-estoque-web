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

import com.made4you.controle.web.entities.StoragePlace;
import com.made4you.controle.web.entities.User;
import com.made4you.controle.web.service.StoragePlaceService;
import com.made4you.controle.web.service.UserService;

@Controller
public class StoragePlaceController {

	@Autowired
	StoragePlaceService storagePlaceService;
	
	@Autowired
	UserService userService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(false);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@RequestMapping("/showStoragePlaceForm")
	public String showStoragePlaceForm(Model model) {
		
		model.addAttribute("storagePlace", new StoragePlace());
		
		return "storage-place-registration";
	}
	
	@RequestMapping("/showStoragePlaceFormForUpdate")
	public String showStoragePlaceFormForUpdate(@RequestParam("storagePlaceId") Long storagePlaceId, Model model) {
		
		StoragePlace storagePlace = storagePlaceService.findyById(storagePlaceId);
		
		model.addAttribute("storagePlace", storagePlace);
		
		return "storage-place-registration";
	}
	
	
	@RequestMapping("/addStoragePlace")
	public String addStoragePlace(@Valid @ModelAttribute StoragePlace storagePlace, BindingResult bindingResult, @RequestParam int userId, Model model) {
		
		User user = userService.findById(userId);
		
		user.add(storagePlace);
		
		StoragePlace storagePlaceExists = storagePlaceService.findByName(storagePlace.getLocationName(), userId);
		
		if(bindingResult.hasErrors()) {
			return "storage-place-registration";
		}
		
		if(storagePlaceExists == null) {
			storagePlaceService.save(storagePlace);
			
			return "redirect:/storagePlaces";
		}
		
		else if(storagePlaceExists.getId() != storagePlace.getId()) {
			model.addAttribute("registrationError", "Já existe local de armazenamento com esse nome");
			
			return "storage-place-registration";
		}
		
		else {
			storagePlaceService.save(storagePlace);
			
			return "redirect:/storagePlaces";
		}
	
	}
	
	@RequestMapping("/storagePlaces")
	public String showStoragePlaces(Model model, HttpServletRequest request) {
		
		HttpSession currentSession = request.getSession();
		
		User user = (User) currentSession.getAttribute("user");
		int userId = user.getId();
		
		List<StoragePlace> storagePlaces = storagePlaceService.findAll(userId);
		model.addAttribute("storagePlaces", storagePlaces);
		
		return "storage-places";
	}
	
	@RequestMapping("/deleteStoragePlace")
	public String deleteStoragePlace(@RequestParam("storagePlaceId") Long storagePlaceId) {
		
		StoragePlace storagePlace = storagePlaceService.findyById(storagePlaceId);
		
		storagePlaceService.delete(storagePlace);
		
		return "redirect:/storagePlaces";
	}
	
	@RequestMapping("searchStoragePlaces")
	public String searchStoragePlaces(Model model, @RequestParam String storagePlaceName, HttpServletRequest request) {
		HttpSession currentSession = request.getSession();
		
		User user = (User) currentSession.getAttribute("user");
		
		int userId = user.getId();
		
		List<StoragePlace> storagePlaces = storagePlaceService.searchStoragePlaces(storagePlaceName, userId);
		
		model.addAttribute("storagePlaces", storagePlaces);
		model.addAttribute("storagePlaceName", storagePlaceName);
		
		return "storage-places";
	}
}
