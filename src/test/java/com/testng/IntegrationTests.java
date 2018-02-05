package test.java.com.testng;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.com.testng.apiRest;
import static org.junit.Assert.*;

public class IntegrationTests {

	
	public IntegrationTests() {}
	
	@BeforeClass
    public static void beforeClass() {
		apiRest.main(new String[] {"xmentestdatabase"});
    }
    
	@After
	public void afterTest() 
	{
		sendGet("http://localhost:4567/eraseMutants");
	}
	
	@Test
	public void simpleIntegrationTestHas200ForCodeResponse() 
	{
		try {
			String dna="{ dna:[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"] }";
			String stat= "{\"count_mutant_dna\":1, \"count_human_dna\":0 \"ratio\":1.0}";
			sendDNAandCheckStat(dna,200,stat);
		}catch(Exception ex)
		{
			fail(ex.toString());
		}
	}

	@Test
	public void simpleIntegrationTestHas300ForCodeResponse() 
	{
		try {
			String dna = "{ dna:[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGTAAG\",\"ACCCTA\",\"TCACTG\"] }";
			String stat = "{\"count_mutant_dna\":0, \"count_human_dna\":1 \"ratio\":0.0}";
			sendDNAandCheckStat(dna,403,stat);
		}catch(Exception ex) 
		{
			fail(ex.toString());
		}
	}
	
	
	private void sendDNAandCheckStat(String dna,int responseCode, String responseStat) 
	{
		try {
			String value = dna;
			int response = sendPost("http://localhost:4567/mutant/",convertStringToUtf8(value));
			assertTrue(responseCode==response);
			
			String getResponse;
			getResponse= sendGet("http://localhost:4567/stat");
			assertTrue(getResponse.contains(responseStat));
			
		}catch(Exception ex) 
		{
			fail(ex.toString());
		}
	}
	
	@Test
	public void sendDNAsAndCheckStat() 
	{
		try {
			
			String dna="{ dna:[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"] }";
			String stat= "{\"count_mutant_dna\":1, \"count_human_dna\":0 \"ratio\":1}";
			
			for (int i =0;i<10;i++) {
				sendDNAandCheckStat(dna,200,stat);
			}
			
			dna = "{ dna:[\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGTAAG\",\"ACCCTA\",\"TCACTG\"] }";
			stat = "{\"count_mutant_dna\":1, \"count_human_dna\":1 \"ratio\":0.5}";
			
			for (int i =0;i<10;i++) {
				sendDNAandCheckStat(dna,403,stat);
			}
		}
		catch(Exception ex) 
		{
			fail(ex.toString());
		}
	}
	
	private String convertStringToUtf8(String value) 
	{
		try {
			byte ptext[] = value.getBytes("ISO-8859-1"); 
			String newValue = new String(ptext, "UTF-8");
			return newValue;
		}
		catch(Exception ex)
		{
			return value;
		}
	}
	
	private String sendGet(String urlToRead) 
	{
		try {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
		}
		catch(Exception ex)
		{
			return ex.toString();
		}
	}
	
	private int sendPost(String _url,String parameters) throws Exception {

		String url = _url;
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
	}

}
