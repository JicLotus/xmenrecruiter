package main.java.com;

import org.apache.log4j.Logger;

import main.java.com.controllers.XMenRecruitmentController;
import main.java.com.services.XMenRecruitmentServices;

public class apiRest {
	

	final static Logger logger = Logger.getLogger(apiRest.class);
	
    public static void main(String[] args) {
    	
    	try 
    	{
    		XMenRecruitmentServices service = args!=null &&args.length>=1 ? new XMenRecruitmentServices(args[0]):new XMenRecruitmentServices();
    		new XMenRecruitmentController(service);
    	}catch(Exception ex) 
    	{
    		logger.error("Intern error",ex);
    	}

    }
}
