package main.java.com.testng;

import static spark.Spark.*;

public class apiRest {
	
	
    public static void main(String[] args) {
    	
    	
    	post("/mutant", (request, response) ->{
    		
    		Human human = new Human();
    		
    		String[] dna = new String[]{"ASDD"};
    		
    		human.isMutant(dna);
    		
    		response.status(200);
    		
    		return request.bodyAsBytes();
    		
    	} );
    }
}
