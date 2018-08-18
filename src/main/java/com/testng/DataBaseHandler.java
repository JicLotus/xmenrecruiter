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
    		MongoClientURI uri = new MongoClientURI("mongodb+srv://connection");
    		
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
	
	
	public void insertHumanDocument(String key,String value) 
	{
		insertDocument(key,value,this.getHumanCollection());
	}
	
	public void insertMutantDocument(String key,String value) 
	{
		insertDocument(key,value,this.getMutantCollection());
	}
	
	private void insertDocument(String key,String value,MongoCollection<Document> collection) 
	{
		Document doc = new Document(key, value);
		if (collection.count(doc)==0) {
			collection.insertOne(doc);
		}
	}
	
	public void eraseCollections()
	{
		Document doc = new Document();
		mutantCollection.deleteMany(doc);
		humanCollection.deleteMany(doc);
	}
	
	public long getMutantCount() 
	{
		return getMutantCollection().count();
	}
	
	public long getHumanCount() 
	{
		return getHumanCollection().count();
	}
	
	private MongoCollection<Document> getMutantCollection()
	{
		return mutantCollection;
	}

	private MongoCollection<Document> getHumanCollection()
	{
		return humanCollection;
	}
	
}
