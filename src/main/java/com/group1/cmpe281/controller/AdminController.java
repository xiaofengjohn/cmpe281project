package com.group1.cmpe281.controller;

import java.util.List;

import com.group1.cmpe281.dao.DataPointDAO;
import com.group1.cmpe281.dao.SensorDAO;
import com.group1.cmpe281.domain.DataPoint;
import com.group1.cmpe281.domain.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.group1.cmpe281.dao.AccountInfoDAO;
import com.group1.cmpe281.domain.AccountInfo;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AccountInfoDAO accountInfoDAO;

	@Autowired
	private DataPointDAO dataPointDAO;

	@Autowired
	private SensorDAO sensorDAO;

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

	@RequestMapping("/sensorListView")
	public String sensorListView(){
		return "sensorListView";
	}

	@RequestMapping("/sensorDataListView")
	public String sensorDataListView(){
		return "sensorDataListView";
	}

	@RequestMapping(value = "/sensor/{id}/data",method = RequestMethod.GET)
	@ResponseBody
	public List<DataPoint> getDataBySensorId(@PathVariable("id")String id){
		return this.dataPointDAO.findAllBySensorId(id);
	}

	@RequestMapping(value = "/sensor",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> getDataBySensorId(){
		return this.sensorDAO.findAll();
	}
	
	@RequestMapping(value = "/sensor/{id}/stop",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> StopSensor(@PathVariable("id")String id){
		this.sensorDAO.stopById(id);
		return this.getDataBySensorId();
	}
	
	@RequestMapping(value = "/sensor/{id}/start",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> startSensor(@PathVariable("id")String id){
		this.sensorDAO.startById(id);
		return this.getDataBySensorId();
	}
	
	@RequestMapping(value = "/sensor/{id}/delete",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> removeSensor(@PathVariable("id")String id){
		this.sensorDAO.deleteById(id);
		return this.getDataBySensorId();
	}

	@ExceptionHandler(value = {Exception.class})
	@ResponseBody
	public String exceptionHandler(Exception exception){
		return exception.getMessage();
	}
	
}
