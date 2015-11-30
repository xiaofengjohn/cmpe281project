package com.group1.cmpe281.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/user/{id}/delete",method = RequestMethod.GET)
	@ResponseBody
	public List<AccountInfo> removeUser(@PathVariable("id")String id){
		this.accountInfoDAO.deleteById(id);
		return this.userList();
	}

	@ExceptionHandler(value = {Exception.class})
	@ResponseBody
	public String exceptionHandler(Exception exception){
		return exception.getMessage();
	}
	
}
