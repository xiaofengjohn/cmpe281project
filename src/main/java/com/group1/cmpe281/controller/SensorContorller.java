package com.group1.cmpe281.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group1.cmpe281.dao.DataPointDAO;
import com.group1.cmpe281.dao.SensorDAO;
import com.group1.cmpe281.domain.DataPoint;
import com.group1.cmpe281.domain.Sensor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiaofengli on 11/30/15.
 */

@Controller
@RequestMapping("/sensor")
public class SensorContorller {

    private DataPointDAO dataPointDAO = new DataPointDAO();

    private SensorDAO sensorDAO = new SensorDAO();

    @RequestMapping(value = "/uploaddata", method = RequestMethod.POST)
    @ResponseBody
    public void upload(@RequestBody DataPoint dataPoint) {
        try {
            System.out.println(new ObjectMapper().writeValueAsString(dataPoint));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
//        dataPointDAO.add(dataPoint);
    }


    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String status(@PathVariable("id")String id) {
        if (true) {
            return "ON";
        }
        Sensor sensor = this.sensorDAO.findById(id);
        if(sensor!=null){
            return sensor.getState();
        }
        return "";
    }


    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public String exceptionHandler(Exception exception){
        return exception.getMessage();
    }

}
