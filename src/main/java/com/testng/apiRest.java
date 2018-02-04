package main.java.com.testng;

import static spark.Spark.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
public class apiRest {
	
	
	final static Logger logger = Logger.getLogger(apiRest.class);
	
	
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> mutantCollection;
	private static MongoCollection<Document> humanCollection;
	
    public static void main(String[] args) {
    	
    	try {
    		MongoClientURI uri = new MongoClientURI("mongodb+srv://jiclotus:1q2w3e4r*@xmenrecruitercluster-3vrfa.mongodb.net/test");
    		
    		mongoClient = new MongoClient(uri);
    		database = mongoClient.getDatabase("xmenrecruiterDB");
    		mutantCollection = database.getCollection("mutantCollection");
    		humanCollection = database.getCollection("humanCollection");
    	}
    	catch(Exception ex) 
    	{
    		logger.error("Error creating MongoDB",ex);
    	}
    	
    	
    	post("/mutant", (request, response) ->{
    		
    		
    		try {
    		Human human = new Human();
    		JSONObject jsonObj = new JSONObject(request.body());
    		
    		JSONArray jsonArray = jsonObj.getJSONArray("dna");
    		
		  int len = jsonArray.length();
		  String[] dna = new String[len];
    		   for (int i=0;i<len;i++){ 
    			   dna[i] = jsonArray.get(i).toString();
    		   }
    		
    		boolean isMutant = human.isMutant(dna);
    		
    		Document doc = new Document("dna", "asd");
    		
    		if (isMutant) 
    		{
    			mutantCollection.insertOne(doc);
    			response.status(200);	
    		}else
    		{
    			humanCollection.insertOne(doc);
    			response.status(403);
    		}
    		
    		}
    		catch(Exception ex)
    		{
    			System.out.println(ex.toString());
    		}
    		
    		return "ASD";
    	} );
    	
    	
    	get("/stat/", (request, response) ->{
    		long countMutant = mutantCollection.count();
    		long countHuman = humanCollection.count();
    		float totalCount = (countMutant+countHuman);
    		float ratio;
    		ratio = (countMutant+countHuman)==0 ? (float )0: (float)(countMutant / totalCount); 
    		
    		return "{\"count_mutant_dna\":"+countMutant+", \"count_human_dna\":"+countHuman+" \"ratio\":"+ratio+"}";
    	});
    	
    	
    	
    }
}
