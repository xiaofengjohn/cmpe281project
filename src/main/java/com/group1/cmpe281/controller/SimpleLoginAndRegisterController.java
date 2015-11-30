package com.group1.cmpe281.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group1.cmpe281.dao.AccountInfoDAO;
import com.group1.cmpe281.domain.AccountInfo;

@Controller
public class SimpleLoginAndRegisterController {
	
	AccountInfoDAO accountInfoDAO = new AccountInfoDAO();

	@RequestMapping(value="/register.html")
	public String registerView(){
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestParam String lastname,
						@RequestParam String firstname,
						@RequestParam String address,
						@RequestParam String email,
						@RequestParam String phone,
						@RequestParam String password,
						@RequestParam String creditCard) {
		AccountInfo accountInfo = new AccountInfo(lastname,firstname,address,email,phone,password,creditCard);
		if (accountInfo!=null&&accountInfoDAO.insert(accountInfo)) {
			return "login";
		} else {
			return "register";
		}
	}
	
	@RequestMapping(value="/login.html")
	public String loginView(){
		return "login";
	}

}
