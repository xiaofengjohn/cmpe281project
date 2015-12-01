package com.group1.cmpe281.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group1.cmpe281.domain.Sensor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaofengli on 11/30/15.
 */
public class SensorDAO {

    private static MongoCollection mongoCollection = null;

    private static ObjectMapper jacksonObjectMapper;

    public SensorDAO() {
        if(jacksonObjectMapper == null) {
            jacksonObjectMapper = new ObjectMapper();
            jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        if(mongoCollection == null) {
            MongoClientURI connectionString = new MongoClientURI("mongodb://sfrogman:1234@ds041623.mongolab.com:41623/cmpe281");
            MongoClient mongoClient = new MongoClient(connectionString);
            MongoDatabase database = mongoClient.getDatabase("cmpe281");
            mongoCollection = database.getCollection("sensor");
        }
    }

    public void add(Sensor sensor){
        try {
            String jsonInString = jacksonObjectMapper.writeValueAsString(sensor);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(String id){
        Document document = (Document) mongoCollection.find(Filters.eq("id", id)).first();
        if(document!=null){
            mongoCollection.deleteMany(document);
        }
    }

    public List<Sensor> findAll(){
        List<Sensor> list = new ArrayList<Sensor>();
        FindIterable<Document> iterable = this.mongoCollection.find();
        for(Document document : iterable) {
            String jsonString = document.toJson();
            Sensor sensor = null;
            try {
                sensor = jacksonObjectMapper.readValue(jsonString, Sensor.class);
                list.add(sensor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<Sensor> findAllByOwnerId(String ownerId){
        List<Sensor> list = new ArrayList<Sensor>();
        FindIterable<Document> iterable = this.mongoCollection.find(new Document("sensorOwnerId",ownerId));
        for(Document document : iterable) {
            String jsonString = document.toJson();
            Sensor sensor = null;
            try {
                sensor = jacksonObjectMapper.readValue(jsonString, Sensor.class);
                list.add(sensor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public Sensor findById(String id){
        if(id==null){
            return null;
        }
        Document document = (Document) mongoCollection.find(new Document("id",id)).first();
        Sensor sensor = null;
        try {
            sensor = jacksonObjectMapper.readValue(document.toJson(), Sensor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sensor;
    }
}
