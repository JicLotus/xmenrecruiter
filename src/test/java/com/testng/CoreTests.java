package test.java.com.testng;



import org.junit.Test;
import static org.junit.Assert.*;

import core.Human;


public class CoreTests {

	
	public CoreTests() 
	{
		
	}
	
	@Test
	public void DnaExamplePass() {
		
		String[] dna = {"ATGCGA"
					   ,"CAGTGC",
					    "TTATGT",
					    "AGAAGG",
					    "CCCCTA",
					    "TCACTG"}; 
		
		Human human = new Human();
		
		boolean isMutant = human.isMutant(dna);
		
		assertTrue(isMutant);
	}

	@Test
	public void DnaExample2Pass() {
		
		String[] dna = {"ATGCGA"
					   ,"CAGTGC",
					    "TTATTT",
					    "AGACGG",
					    "GCGGGG",
					    "TCTTTT"}; 
		
		Human human = new Human();
		
		assertTrue(human.isMutant(dna));
	}
	
	
	@Test
	public void DnaExample3Fail() {
		
		String[] dna = {"ATGCGA"
					   ,"CAGTGC",
					    "TTATTT",
					    "AGACGG",
					    "GCTTTT",
					    "TCTTTT"}; 
		
		Human human = new Human();
		
		assertFalse(human.isMutant(dna));
	}
	
	@Test
	public void DnaExampleFail() {
		
		String[] dna = {"ATGCAA"
					   ,"CAGTGC",
					    "TTATGT",
					    "AGACGG",
					    "CTCCTA",
					    "TCACTG"}; 
		
		Human human = new Human();
		
		assertFalse(human.isMutant(dna));
	}
	
	
	@Test
	public void DnaExample2Fail() {
		
		String[] dna = {"ATGCGA"
					   ,"CAGTGC",
					    "TTATTT",
					    "AGACGG",
					    "GCGTCA",
					    "TCACTG"}; 
		
		Human human = new Human();
		
		assertFalse(human.isMutant(dna));
	}
	
}
