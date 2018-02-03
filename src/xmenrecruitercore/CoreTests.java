package xmenrecruitercore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoreTests {

	@Test
	void DnaExamplePass() {
		
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
	void DnaExample2Pass() {
		
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
	void DnaExample3Fail() {
		
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
	void DnaExampleFail() {
		
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
	void DnaExample2Fail() {
		
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
