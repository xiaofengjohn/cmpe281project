package com.group1.cmpe281.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/virtualSensor")
public class VirtualSensorController {

	@RequestMapping(value = "/virtualSensorView",method = RequestMethod.GET)
	public String virtualSensorView(){
		return "virtualSensorView";
	}
	
}
