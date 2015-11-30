package com.group1.cmpe281.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group1.cmpe281.dao.AccountInfoDAO;
import com.group1.cmpe281.domain.AccountInfo;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	AccountInfoDAO accountInfoDAO = new AccountInfoDAO();

	@RequestMapping("/")
	public String homeView(){
		return "adminHome";
	}
	
	@RequestMapping(value = "/userListView",method = RequestMethod.GET)
	public String userListView(){
		return "userListView";
	}
	
	@RequestMapping(value = "/userList",method = RequestMethod.GET)
	@ResponseBody
	public List<AccountInfo> userList(){
		return this.accountInfoDAO.findAll();
	}
	
}
