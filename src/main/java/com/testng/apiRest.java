package main.java.com.testng;

import static spark.Spark.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class apiRest {
	
	
    public static void main(String[] args) {
    	
    	
    	post("/mutant", (request, response) ->{
    		
    		Human human = new Human();
    		JSONObject jsonObj = new JSONObject(request.body());
    		
    		JSONArray jsonArray = jsonObj.getJSONArray("dna");
    		
		  int len = jsonArray.length();
		  String[] dna = new String[len];
    		   for (int i=0;i<len;i++){ 
    			   dna[i] = jsonArray.get(i).toString();
    		   }
    		
    		boolean isMutant = human.isMutant(dna);
    		
    		if (isMutant) 
    		{
    			response.status(200);	
    		}else
    		{
    			response.status(403);
    		}
    		
    		
    		return "";
    		
    	} );
    }
}
