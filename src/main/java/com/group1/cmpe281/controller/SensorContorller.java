package com.group1.cmpe281.controller;

import com.group1.cmpe281.dao.DataPointDAO;
import com.group1.cmpe281.dao.SensorDAO;
import com.group1.cmpe281.domain.DataPoint;
import com.group1.cmpe281.domain.Sensor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiaofengli on 11/30/15.
 */

@Controller
@RequestMapping("/sensor")
public class SensorContorller {

    @Autowired
    private DataPointDAO dataPointDAO;

    @Autowired
    private SensorDAO sensorDAO;

    @RequestMapping(value = "/uploaddata", method = RequestMethod.POST)
    @ResponseBody
    public Object upload(@RequestBody DataPoint dataPoint) {
    	String sensorId = dataPoint.getSensorId();
    	Sensor sensor = this.sensorDAO.findById(sensorId);
    	dataPoint.setSensorOwnerId(sensor.getSensorOwnerId());
        dataPointDAO.add(dataPoint);
        return new String[]{"SUCCESS"};
    }


    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object status(@PathVariable("id")String id) {
        Sensor sensor = this.sensorDAO.findById(id);
        if(sensor!=null){
            return new String[]{sensor.getState()};
        }
        return new String[]{"OFF"};
    }

}
