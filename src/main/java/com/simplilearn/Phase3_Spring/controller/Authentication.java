package com.simplilearn.Phase3_Spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.simplilearn.Phase3_Spring.dao.DAO;
import com.simplilearn.Phase3_Spring.model.User;

@Controller
public class Authentication {
	
	@Autowired
	DAO dao;
	
	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("user", new User());
	    return "index";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("user") User user, Model model) {
		
		String us = user.getName();
		String pw = user.getPassword();
		
		String resultpw = dao.authenticate(us, pw);
				
		model.addAttribute("user", user);
		
		if(pw.equals(resultpw)) {
			return "welcome";
		}else {
			model.addAttribute("error", "Password invalid");
			return "index";
		}
		
		
	}
	
	@PostMapping("/changePW")
	public String changePW(@ModelAttribute("user") User user, Model model) {
		
		String us = user.getName();
		String pw = user.getPassword();
		
		int results = dao.changePw(us, pw);
		System.out.println("Results: " + results);
		
		if(results == 1) {
			model.addAttribute("message", "Password updated");
		}else {
			model.addAttribute("message", "Password not updated");
		}
		
		model.addAttribute("user", user);
		
		return "welcome";
	}

}
