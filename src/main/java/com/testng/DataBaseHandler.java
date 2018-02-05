package main.java.com.testng;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DataBaseHandler {

	final static Logger logger = Logger.getLogger(DataBaseHandler.class);
	
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	private static MongoCollection<Document> mutantCollection;
	private static MongoCollection<Document> humanCollection;
	
	public DataBaseHandler(String dbName) 
	{
    	try {
    		MongoClientURI uri = new MongoClientURI("mongodb+srv://jiclotus:1q2w3e4r*@xmenrecruitercluster-3vrfa.mongodb.net/test");
    		
    		mongoClient = new MongoClient(uri);
    		database = mongoClient.getDatabase(dbName);
    		mutantCollection = database.getCollection("mutantCollection");
    		humanCollection = database.getCollection("humanCollection");
    	}
    	catch(Exception ex) 
    	{
    		logger.error("Error in Mongo database",ex);
    	}
	}
	
	public void eraseCollections()
	{
		Document doc = new Document();
		mutantCollection.deleteMany(doc);
		humanCollection.deleteMany(doc);
	}
	
	public MongoCollection<Document> getMutantCollection()
	{
		return mutantCollection;
	}

	public MongoCollection<Document> getHumanCollection()
	{
		return humanCollection;
	}
	
}
