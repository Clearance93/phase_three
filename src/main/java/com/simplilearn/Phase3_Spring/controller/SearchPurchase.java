package com.simplilearn.Phase3_Spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.simplilearn.Phase3_Spring.dao.DAO;
import com.simplilearn.Phase3_Spring.model.Purchase;
import com.simplilearn.Phase3_Spring.model.User;

@Controller
public class SearchPurchase {

	@Autowired
	DAO dao;
	
	@GetMapping("/Purchase")
	public String listPurchase(Model model) {
		
		List<Purchase> purchases = dao.searchAllPurchases();
		
		List<String> productCategory = new ArrayList<String>();
		List<String> timestamp = new ArrayList<String>();
		
		for(Purchase p : purchases){

			if(!productCategory.contains(p.getProductCategory())) {
				productCategory.add(p.getProductCategory());
			}
			
			String date = p.getTimestamp().toString();
			
			if(!timestamp.contains(date)) {
				
				timestamp.add(date);
			}
		}
		
		model.addAttribute("categories", productCategory);
		model.addAttribute("timestamp", timestamp);
			
		return "Purchases";		
	}
	
	@PostMapping("/PurchaseByDate")
	public String PurchaseByDate(@ModelAttribute("timestamp") String ts, Model model) {
		
		List<Purchase> purchases = dao.searchPurchasesByDate(ts);

		model.addAttribute("purchases", purchases);			
			
		return "ListPurchases";		
	}
	
	@PostMapping("/PurchaseByCategory")
	public String PurchaseByCategory(@ModelAttribute("category") String category, Model model) {
		
		List<Purchase> purchases = dao.searchPurchasesByCategory(category);

		model.addAttribute("purchases", purchases);			
			
		return "ListPurchases";		
	}
}
