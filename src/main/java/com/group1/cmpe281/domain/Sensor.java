package com.group1.cmpe281.domain;

import org.bson.types.ObjectId;

/**
 * Created by xiaofengli on 11/30/15.
 */
public class Sensor {

    private String id = new ObjectId().toString();

    private String sensorOwnerId;

    private String sensorName;

    private String sensorOwnerUsername;

    // Just has to status ON and OFF
    private String state = "ON";


    public Sensor() {

    }

    public Sensor(String sensorOwnerId, String sensorOwnerUsername, String sensorName) {
        this.sensorOwnerId = sensorOwnerId;
        this.sensorOwnerUsername = sensorOwnerUsername;
        this.sensorName = sensorName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}