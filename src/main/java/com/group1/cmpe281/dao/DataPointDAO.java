package com.group1.cmpe281.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group1.cmpe281.domain.DataPoint;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaofengli on 11/30/15.
 */
public class DataPointDAO {

    static MongoCollection mongoCollection = null;

    static ObjectMapper jacksonObjectMapper;

    public DataPointDAO() {
        if(jacksonObjectMapper == null) {
            jacksonObjectMapper = new ObjectMapper();
            jacksonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        if(mongoCollection == null) {
            MongoClientURI connectionString = new MongoClientURI("mongodb://sfrogman:1234@ds041623.mongolab.com:41623/cmpe281");
            MongoClient mongoClient = new MongoClient(connectionString);
            MongoDatabase database = mongoClient.getDatabase("cmpe281");
            mongoCollection = database.getCollection("data");
        }
    }

    public void add(DataPoint dataPoint){
        try {
            String jsonInString = jacksonObjectMapper.writeValueAsString(dataPoint);
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

    public List<DataPoint> findAllBySensorId(String sensorId){
        List<DataPoint> list = new ArrayList<DataPoint>();
        FindIterable<Document> iterable = this.mongoCollection.find(new Document("sensorId",sensorId));
        for(Document document : iterable) {
            String jsonString = document.toJson();
            DataPoint data = null;
            try {
                data = jacksonObjectMapper.readValue(jsonString, DataPoint.class);
                list.add(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public List<DataPoint> findAllBySensorOwnerId(String sensorOwnerId){
        List<DataPoint> list = new ArrayList<DataPoint>();
        FindIterable<Document> iterable = this.mongoCollection.find(new Document("sensorOwnerId",sensorOwnerId));
        for(Document document : iterable) {
            String jsonString = document.toJson();
            DataPoint data = null;
            try {
                data = jacksonObjectMapper.readValue(jsonString, DataPoint.class);
                list.add(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public DataPoint findById(String id){
        if(id==null){
            return null;
        }
        Document document = (Document) mongoCollection.find(new Document("id",id)).first();
        DataPoint data = null;
        try {
            data = jacksonObjectMapper.readValue(document.toJson(), DataPoint.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<DataPoint> findAbnormalByName(String name) {
        List<DataPoint> result = new ArrayList<DataPoint>();
        Bson abnormalTemp = Filters.gt("temperature", 37);
        Bson abnormalHeart = Filters.or(Filters.lt("heartrate", 60), Filters.gt("heartrate", 100));
        Bson abnormalBlood = Filters.or(Filters.lt("bloodpressure", 80), Filters.gt("bloodpressure", 120));
        Bson abnormal = Filters.or(abnormalBlood, abnormalHeart, abnormalTemp);
        FindIterable<Document> documents = mongoCollection.find(Filters.and(Filters.eq("name", name), abnormal))
                .sort(new Document().append("timeStamp", 1));
        for(Document document : documents) {
            String jsonString = document.toJson();
            DataPoint dataPoint = null;
            try {
                dataPoint = jacksonObjectMapper.readValue(jsonString, DataPoint.class);
                result.add(dataPoint);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


}