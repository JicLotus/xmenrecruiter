package main.java.com;

import main.java.com.algorithm.MutantAlgorithm;
import main.java.com.algorithm.SameStaticRowColMutantAlgorithm;

public class Human {

	String elements;
	private MutantAlgorithm mutantAlgorithm;
	
	public Human() 
	{
		elements="";
		mutantAlgorithm = new SameStaticRowColMutantAlgorithm();
	}
	
	private boolean hasDnaElement(char c)
	{
		return elements.indexOf(c)>=0;
	}
	
	private void addDnaElement(char c) {
		elements+=c;
	}

	private boolean amIAMutant() 
	{
		return elements.length()>1;
	}
	
	private boolean isAValidChar(char _char) 
	{
		return _char=='A'||_char=='T' ||_char=='G'||_char=='C';
	}
	
	private boolean isAValidDna(String[] dna) 
	{
		int height=dna.length;
		for (int i =0;i< dna.length;i++) 
		{
			String actualString = dna[i];
			if (actualString.length()!=height)return false;
		}
		return true;
	}
	
	private boolean mainConditions(char actualChar,int i,int j, String[] dna) 
	{
		return isAValidChar(actualChar) && !hasDnaElement(actualChar) && mutantAlgorithm.hasMutantSecuence(i,j,dna);
	}
	
	public boolean isMutant(String[] dna) 
	{
		if (!isAValidDna(dna)) 
		{
			return false;
		}
		
		for (int i =0;i< dna.length;i++) 
		{
			String actualString = dna[i];
			for(int j=0;j<actualString.length();j++) 
			{
				char actualChar= actualString.charAt(j);
				
				if (mainConditions(actualChar,i,j,dna)) {
					addDnaElement(actualChar);
					if (amIAMutant()) 
					{
						return true;
					}
				}
			}
		}
		return false;	
	}
	
}
