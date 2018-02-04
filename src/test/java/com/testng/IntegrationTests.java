package test.java.com.testng;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;

import main.java.com.testng.apiRest;
import static org.junit.Assert.*;

public class IntegrationTests {

	
	public IntegrationTests() {}
	
	@BeforeClass
    public static void beforeClass() {
		apiRest.main(null);
    }
    
	
	@Test
	public void simpleIntegrationTestHas200ForCodeResponse() 
	{
		try {
			String value = "{ dna:[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"] }";
			byte ptext[] = value.getBytes("ISO-8859-1"); 
			String postData = new String(ptext, "UTF-8");
			int response = sendPost(postData);
			assertTrue(200==response);
		}catch(Exception ex)
		{
			fail(ex.toString());
		}
	}

	@Test
	public void simpleIntegrationTestHas300ForCodeResponse() 
	{
		try {
			String value = "{ dna:[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGTAAG\",\"ACCCTA\",\"TCACTG\"] }";
			byte ptext[] = value.getBytes("ISO-8859-1"); 
			String postData = new String(ptext, "UTF-8");
			int response = sendPost(postData);
			assertTrue(403==response);
		}catch(Exception ex) 
		{
			fail(ex.toString());
		}
	}
	
	
	
	private int sendPost(String parameters) throws Exception {

		String url = "http://localhost:4567/mutant";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = parameters;

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		return responseCode;
		
		/*
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();*/

		//print result
		//System.out.println(response.toString());
		
		
	}

}
