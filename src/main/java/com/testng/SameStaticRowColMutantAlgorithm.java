package main.java.com.testng;

public class SameStaticRowColMutantAlgorithm implements MutantAlgorithm {

	static int mutantSecuence=4;
	
	private boolean firstCondition(String actualString,char actualChar,int j) 
	{
		boolean hasMutantSecuenceLength = actualString.length()>j+mutantSecuence-1;
		if (!hasMutantSecuenceLength) return false;
		
		for(int index=0;index<mutantSecuence-1;index++) 
		{
			if (actualString.charAt(j+index+1)!=actualChar) return false;
		}
		return  true;
	}
	
	private boolean secondCondition(String[] actualStrings,char actualChar,int j) 
	{
		for (String actualString : actualStrings) 
		{
			if (actualString.charAt(j)!=actualChar) return false;
		}
		return true;
	}
	
	private boolean thirdCondition(String[] actualStrings,char actualChar,int j) 
	{
		int index=1;
		for (String actualString : actualStrings) 
		{
			boolean mutantSecuenceLength = actualString.length()>j+mutantSecuence-1;
			if (!mutantSecuenceLength) return false;
			if (actualString.charAt(j+index)!=actualChar) return false;
			index++;
		}
		return true;
	}
	
	
	private boolean fourthCondition(String[] actualStrings,char actualChar,int j) 
	{
		boolean mutantSecuenceLength1 = j-(mutantSecuence-1)>0;
		if (!mutantSecuenceLength1) return false;
		
		int index=1;
		for (String actualString : actualStrings) 
		{
			if (actualString.charAt(j-index)!=actualChar) return false;
			index++;
		}
		return true;
	}
	
	public boolean hasMutantSecuence(int i,int j,String[] dna) 
	{
		String actualString = dna[i];
		
		char actualChar= actualString.charAt(j);
		
		if (firstCondition(actualString,actualChar,j))
		{
			return true;
		} 
		
		if (dna.length<=i+mutantSecuence-1) 
		{
			return false;
		}
		
		String[] actualStrings= new String[mutantSecuence-1];
		for (int index=0;index<mutantSecuence-1;index++) 
		{
			actualStrings[index] = dna[i+index+1];
		}
		
		if (secondCondition(actualStrings,actualChar,j))
		{
			return true;
		}
		if (thirdCondition(actualStrings,actualChar,j))
		{
			return true;
		}
		if (fourthCondition(actualStrings,actualChar,j))
		{
			return true;
		}
		
		return false;
	}
	
	
}
