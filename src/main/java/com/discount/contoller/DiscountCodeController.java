package com.discount.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.discount.service.DiscountService;

@RestController
public class DiscountCodeController {
	
	@Autowired
	DiscountService discountService;
	
	@GetMapping("/dicountCode")
	public Object getDiscountCode(@RequestParam String name, @RequestParam String email) {
		Object discountCode = discountService.getDiscountCode(name,email);
		return discountCode;
	}
	
	@GetMapping("/getPercentage")
	public Object getDiscountPercentage(@RequestParam String email, @RequestParam String discountCode) {
		Object percentage = discountService.getgetDiscountCode(email,discountCode);
		return percentage;
	}

}
