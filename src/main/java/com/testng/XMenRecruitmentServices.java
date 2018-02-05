package main.java.com.testng;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;

public class XMenRecruitmentServices {

	final static Logger logger = Logger.getLogger(XMenRecruitmentServices.class);	
	DataBaseHandler dataBase;
	
	public XMenRecruitmentServices(String dbName) 
	{
		dataBase = new DataBaseHandler(dbName);
	}

	public XMenRecruitmentServices() 
	{
		dataBase = new DataBaseHandler("xmenrecruiterDB");
	}
	
	public boolean isAMutantDNA(String jsonDna) 
	{
		try {
			Human human = new Human();
			String[] dna = getStringArrayFromJsonArrayString(jsonDna);
			String flatDna = String.join("", dna);
			
			boolean isMutant = human.isMutant(dna);
			
			if (isMutant) 
			{
				dataBase.insertMutantDocument("dna",flatDna);
			}else
			{
				dataBase.insertHumanDocument("dna",flatDna);
			}
			
			return isMutant;
		}
		catch(Exception ex)
		{
			logger.error("Error in isAMutantDNA: ",ex);
			return false;
		}
	}
	
	
	public String[] getStringArrayFromJsonArrayString(String jsonDna) 
	{
		JSONObject jsonObj = new JSONObject(jsonDna);
		JSONArray jsonArray = jsonObj.getJSONArray("dna");
		
		int len = jsonArray.length();
		String[] stringArray = new String[len];
		for (int i=0;i<len;i++){ 
			stringArray[i] = jsonArray.get(i).toString();
		}
		
		return stringArray;
	}
	
	public boolean eraseCollections() 
	{
		try {
			dataBase.eraseCollections();
			return true;
		}
		catch(Exception ex) 
		{
			logger.error("Error in eraseCollection: ",ex);
			return false;
		}
		
	}
	
	
	public String getStats() {
		try {
			long countMutant = dataBase.getMutantCount();
			long countHuman = dataBase.getHumanCount();
			float ratio;
			ratio = (countHuman)==0 ? (float )0: (float)(countMutant / countHuman);
			return "{\"count_mutant_dna\":"+countMutant+", \"count_human_dna\":"+countHuman+" \"ratio\":"+ratio+"}";
		}
		catch(Exception ex)
		{
			logger.error("Error in getStats: ",ex);
			return "{\"Error\":" + ex.toString() + "}";	
		}
		
	}
	
	
}
