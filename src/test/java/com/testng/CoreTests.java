package test.java.com.testng;

import org.junit.Test;

import main.java.com.testng.Human;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class CoreTests {

	
	public CoreTests() 
	{
		
	}
	
	@Test
	public void ItIsNotMutantDnaBecauseItIsNotSqaredMatrix() 
	{
		String[] dna = {"AAAA",
						"CCCC"}; 
	
		Human human = new Human();
	
		boolean isMutant = human.isMutant(dna);
	
		assertFalse(isMutant);
	}
	
	
	@Test
	public void ItIsNotMutantDnaBecauseItHasInvalidCharSecuence() 
	{
		String[] dna = {".TGCTA",
						"C.GTGC",
						"TT.TGT",
						"AGA.GG",
						"CCCCTA",
				    	"TCACTT"}; 
	
		Human human = new Human();
	
		boolean isMutant = human.isMutant(dna);
	
		assertFalse(isMutant);
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
	
	
	@Test
	public void DnaExample3Pass() {
		
		String[] dna = {"ATGCGA"
					   ,"CAGTAC",
					    "TTAATT",
					    "AGACGG",
					    "GCGTCA",
					    "TCTTTT"}; 
		
		Human human = new Human();
		boolean response = human.isMutant(dna);
		assertTrue(response);
	}
	
	@Test
	public void DnaExample4Pass() {
		
		String[] dna = {"ATGCGA"
					   ,"CAGTAC",
					    "TTAATG",
					    "AGACGG",
					    "GCGGCA",
					    "TCGTGT"}; 
		
		Human human = new Human();
		boolean response = human.isMutant(dna);
		assertTrue(response);
	}
	
	@Test
	public void DnaExample4Fail() {
		
		String[] dna = {"ATGCGA"
					   ,"CAGTAC",
					    "TTAATG",
					    "AGACGG",
					    "GCGTCA",
					    "TCGTGT"}; 
		
		Human human = new Human();
		boolean response = human.isMutant(dna);
		assertFalse(response);
	}
	
	
	@Test
	public void DnaStress() {
		
		String fileName = "stressMatrix.txt";

		try {
		
			Stream<String> stream = Files.lines(Paths.get(fileName));

			Object[] dnaO = stream.toArray();
			
		    String[] dna = new String[dnaO.length];
		    for (int i = 0; i < dnaO.length; i++)
		    	dna[i] = String.valueOf(dnaO[i]);
			
			Human human = new Human();
			boolean response = human.isMutant(dna);
			
			assertTrue(response);
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
}
