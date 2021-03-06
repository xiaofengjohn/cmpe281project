package com.group1.cmpe281.controller;

import com.group1.cmpe281.dao.AccountInfoDAO;
import com.group1.cmpe281.dao.DataPointDAO;
import com.group1.cmpe281.dao.SensorDAO;
import com.group1.cmpe281.domain.AccountInfo;
import com.group1.cmpe281.domain.DataPoint;
import com.group1.cmpe281.domain.Sensor;
import com.group1.cmpe281.domain.Usage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private SensorDAO sensorDAO;

	@Autowired
	private DataPointDAO dataPointDAO;

	@Autowired
	private AccountInfoDAO accountInfoDAO;

	@RequestMapping("/")
	public String homeView(){
		return "home";
	}

	@RequestMapping("/addSensorView")
	public String addSensorView(){
		return "addSensorView";
	}

	@RequestMapping(value = "/sensor",method = RequestMethod.POST)
	@ResponseBody
	public String addSensor(@RequestBody String sensorName){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		AccountInfo accountInfo = accountInfoDAO.getAccountInfo(name);
		if(accountInfo==null){
			throw new RuntimeException("Cannot find use info with name : " + name);
		}
		Sensor sensor = new Sensor();
		sensor.setSensorName(sensorName);
		sensor.setSensorOwnerId(accountInfo.getId());
		sensor.setSensorOwnerUsername(accountInfo.getUsername());
		this.sensorDAO.add(sensor);
		return "";
	}

	@RequestMapping("/sensorListView")
	public String sensorListView(){
		return "sensorListView";
	}

	@RequestMapping(value = "/sensor",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> sensorList(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		AccountInfo accountInfo = accountInfoDAO.getAccountInfo(name);
		if(accountInfo==null){
			throw new RuntimeException("Cannot find use info with name : " + name);
		}
		return this.sensorDAO.findAllByOwnerId(accountInfo.getId());
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

	@RequestMapping(value = "/sensor/{id}",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> searchSensor(@PathVariable("id")String id){
		Sensor sensor = this.sensorDAO.findById(id);
		List<Sensor> list = new ArrayList<Sensor>();
		list.add(sensor);
		return list;
	}

	@RequestMapping(value = "/sensor/{id}/stop",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> StopSensor(@PathVariable("id")String id){
		this.sensorDAO.stopById(id);
		return this.sensorList();
	}
	
	@RequestMapping(value = "/sensor/{id}/start",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> startSensor(@PathVariable("id")String id){
		this.sensorDAO.startById(id);
		return this.sensorList();
	}
	
	@RequestMapping(value = "/sensor/{id}/delete",method = RequestMethod.GET)
	@ResponseBody
	public List<Sensor> removeSensor(@PathVariable("id")String id){
		this.sensorDAO.deleteById(id);
		return this.sensorList();
	}
	
	@RequestMapping("/usageView")
	public String usageView(){
		return "usageView";
	}
	
	@RequestMapping(value = "/usage",method = RequestMethod.GET)
	@ResponseBody
	public Usage getUsage(){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		AccountInfo accountInfo = accountInfoDAO.getAccountInfo(name);
		if(accountInfo==null){
			throw new RuntimeException("Cannot find use info with name : " + name);
		}
		int credit = accountInfo.getCredit();
		int used = dataPointDAO.getDatasizeBySensorOwnerId(accountInfo.getId());
		return new Usage(used, credit);
	}
	
	@RequestMapping(value = "/credit/{credit}",method = RequestMethod.GET)
	@ResponseBody
	public Usage credit(@PathVariable("credit")int credit){
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		AccountInfo accountInfo = accountInfoDAO.getAccountInfo(name);
		if(accountInfo==null){
			throw new RuntimeException("Cannot find use info with name : " + name);
		}
		this.accountInfoDAO.addCreditById(accountInfo.getId(), credit);
		int used = dataPointDAO.getDatasizeBySensorOwnerId(accountInfo.getId());
		return new Usage(used, accountInfo.getCredit() + credit);
	}

}
