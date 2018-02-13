package main.java.com.services;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import main.java.com.DataBaseHandler;
import main.java.com.Human;
import main.java.com.responses.ErrorResponse;
import main.java.com.responses.StatResponse;
import com.google.gson.Gson;

import main.java.com.models.Dna;

public class XMenRecruitmentServices {

	final static Logger logger = Logger.getLogger(XMenRecruitmentServices.class);	
	DataBaseHandler dataBase;
	Gson gson;
	
	public XMenRecruitmentServices(String dbName) 
	{
		dataBase = new DataBaseHandler(dbName);
		gson = new Gson();
	}

	public XMenRecruitmentServices() 
	{
		dataBase = new DataBaseHandler("xmenrecruiterDB");
	}
	
	public boolean isAMutantDNA(String jsonDna) 
	{
		try {
			Human human = new Human();
			Dna dnaClass = gson.fromJson(jsonDna, Dna.class);
			String[] dna = dnaClass.getDna();
			
			String flatDna = String.join("", dna);
			boolean isMutant = human.isMutant(dna);
			
			if (isMutant) 
			{
				dataBase.insertMutantDocument(flatDna,jsonDna);
			}else
			{
				dataBase.insertHumanDocument(flatDna,jsonDna);
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
			long countMutant = dataBase.getMutantCount();
			long countHuman = dataBase.getHumanCount();
			
			float ratio;
			ratio = (countHuman)==0 ? (float )0: (float)(countMutant / countHuman);
			
			StatResponse statResponse = new StatResponse();
			statResponse.setCountMutant(countMutant);
			statResponse.setCountHuman(countHuman);
			statResponse.setRatio(ratio);
			
			return gson.toJson(statResponse);
		}
		catch(Exception ex)
		{
			logger.error("Error in getStats: ",ex);
			ErrorResponse error = new ErrorResponse();
			error.setDescription(ex.toString());
			return gson.toJson(error);
		}
		
	}
	
	
}
