package com.group1.cmpe281.controller;

import com.group1.cmpe281.dao.SensorDAO;
import com.group1.cmpe281.domain.Sensor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	private SensorDAO sensorDAO = new SensorDAO();

	@RequestMapping("/")
	public String homeView(){
		return "home";
	}

	@RequestMapping("/addSensorView")
	public String addSensorView(){
		return "addSensorView";
	}

	@RequestMapping("/sensorListView")
	public String sensorListView(){
		return "sensorListView";
	}



	@RequestMapping(value = "/sensor/{id}/delete",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> removeSensor(@PathVariable("id")String id){
		this.sensorDAO.deleteById(id);
		return this.sensorDAO.findAll();
	}

}
