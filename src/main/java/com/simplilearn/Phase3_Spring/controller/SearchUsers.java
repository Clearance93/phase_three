package com.simplilearn.Phase3_Spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.simplilearn.Phase3_Spring.dao.DAO;
import com.simplilearn.Phase3_Spring.model.User;


@Controller
public class SearchUsers {

	@Autowired
	DAO dao;
	
	@PostMapping("/listUsers")
	public String listUser(Model model) {
		List<User> users =  dao.getUsers();
		model.addAttribute("users",users);
		
		return "listUser";		
	}
	
	
	@PostMapping("/searchUser")
	public String searchUser(@ModelAttribute("user") User user, Model model) {
		String userName = user.getSearchName();
		//System.out.println("Username: " + userName);
		String u =  dao.searchUser(userName);
		
		if(u.equals(userName)) {
			model.addAttribute("userFound", "User " + user.getSearchName()+" found");
		}else {
			model.addAttribute("userFound", "User " + user.getSearchName() +" not found");
		}
		//model.addAttribute("user",user);
		//System.out.println("Searchuser: " + user.getName());
		//System.out.println("userName: " + user.getSearchName());
				
		return "searchUser";		
	}
}
