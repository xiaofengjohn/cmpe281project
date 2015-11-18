package com.springapp.mvc.Data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * Created by WU on 17/11/2015.
 */
public class DataDAO {
    static MongoCollection mongoCollection = null;
    static ObjectMapper jacksonObjectMapper;
    public DataDAO() {
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
    public List<DataPoint> findByName(String name) {
        List<DataPoint> result = new ArrayList<DataPoint>();
        FindIterable<Document> documents = mongoCollection.find(Filters.eq("name", name));
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

    public boolean insert(DataPoint dataPoint) {
        try {
            String jsonInString = jacksonObjectMapper.writeValueAsString(dataPoint);
            Document doc = Document.parse(jsonInString);
            mongoCollection.insertOne(doc);
            return true;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteByName(String name) {
        Document document = new Document();
        document.append("name", name);
        mongoCollection.deleteMany(document);
        return true;
    }
}
