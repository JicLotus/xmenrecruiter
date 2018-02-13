package main.java.com.controllers;

import static spark.Spark.get;
import static spark.Spark.post;

import main.java.com.services.XMenRecruitmentServices;




public class XMenRecruitmentController {

	public XMenRecruitmentController(final XMenRecruitmentServices xMenRecruitmentServices) 
	{
		post("/mutant/", (request, response) ->{
			boolean serviceResponse=xMenRecruitmentServices.isAMutantDNA(request.body());
			
			if (serviceResponse) response.status(200);
			else response.status(403);
			
			return "";
		});
		
    	get("/stat", (request, response) -> {
    		response.type("application/json");
    		return xMenRecruitmentServices.getStats();
    	});
    	
    	
    	get("/eraseMutants", (request, response) -> xMenRecruitmentServices.eraseCollections());
	}
}


