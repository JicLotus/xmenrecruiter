package main.java.com.testng;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

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
			JSONObject jsonObj = new JSONObject(jsonDna);
			
			JSONArray jsonArray = jsonObj.getJSONArray("dna");
    		
			int len = jsonArray.length();
			String[] dna = new String[len];
			String flatDna="";
			for (int i=0;i<len;i++){ 
				dna[i] = jsonArray.get(i).toString();
				flatDna+=dna[i];
			}
			
			boolean isMutant = human.isMutant(dna);
			Document doc = new Document("dna", flatDna);
			
			if (isMutant) 
			{
				dataBase.getMutantCollection().insertOne(doc);
			}else
			{
				dataBase.getHumanCollection().insertOne(doc);
			}
			
			return isMutant;
		}
		catch(Exception ex)
		{
			logger.error("Error in isAMutantDNA: ",ex);
			return false;
		}
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
			long countMutant = dataBase.getMutantCollection().count();
			long countHuman = dataBase.getHumanCollection().count();
			float totalCount = (countMutant+countHuman);
			float ratio;
			ratio = (countMutant+countHuman)==0 ? (float )0: (float)(countMutant / totalCount);
			return "{\"count_mutant_dna\":"+countMutant+", \"count_human_dna\":"+countHuman+" \"ratio\":"+ratio+"}";
		}
		catch(Exception ex)
		{
			logger.error("Error in getStats: ",ex);
			return "{\"Error\":" + ex.toString() + "}";	
		}
		
	}
	
	
}
