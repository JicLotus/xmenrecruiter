package main.java.com.testng;

import org.apache.log4j.Logger;

public class apiRest {
	

	final static Logger logger = Logger.getLogger(apiRest.class);
	
    public static void main(String[] args) {
    	
    	try 
    	{
    		new XMenRecruitmentController(new XMenRecruitmentServices());
    	}catch(Exception ex) 
    	{
    		logger.error("Intern error",ex);
    	}

    }
}
