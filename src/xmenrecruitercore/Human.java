package xmenrecruitercore;

import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class Human {

	String elements;
	static int mutantSecuence=4;
	
	public Human() 
	{
		elements="";
	}
	
	private boolean hasDnaElement(char c)
	{
		return elements.indexOf(c)>=0;
	}
	
	private void addDnaElement(char c) {
		elements+=c;
	}
	
	
	private boolean firstCondition(String actualString,char actualChar,int j) 
	{
		boolean thirdLength = actualString.length()>j+3;
		if (!thirdLength) return false;
		
		return actualString.charAt(j+1)==actualChar && actualString.charAt(j+2)==actualChar && actualString.charAt(j+3)==actualChar;
	}
	
	
	private boolean secondCondition(String actualString1,String actualString2,String actualString3,char actualChar,int j) 
	{
		
		return actualString1.charAt(j)==actualChar && actualString2.charAt(j)==actualChar && actualString3.charAt(j)==actualChar;
	}
	
	
	private boolean thirdCondition(String actualString1,String actualString2,String actualString3,char actualChar,int j) 
	{
		boolean thirdLength1 = actualString1.length()>j+3;
		if (!thirdLength1) return false;
		boolean thirdLength2 = actualString2.length()>j+3;
		if (!thirdLength2) return false;
		boolean thirdLength3 = actualString3.length()>j+3;
		if (!thirdLength3) return false;
		
		return actualString1.charAt(j+1)==actualChar && actualString2.charAt(j+2)==actualChar && actualString3.charAt(j+3)==actualChar;
	}
	
	
	private boolean fourthCondition(String actualString1,String actualString2,String actualString3,char actualChar,int j) 
	{
		boolean thirdLength1 = j-3>0;
		if (!thirdLength1) return false;
		
		return actualString1.charAt(j-1)==actualChar && actualString2.charAt(j-2)==actualChar && actualString3.charAt(j-3)==actualChar;
	}
	
	
	private boolean hasMutantSecuence(int i,int j,String[] dna) 
	{
		String actualString = dna[i];
		
		char actualChar= actualString.charAt(j);
		
		if (firstCondition(actualString,actualChar,j))
		{
			return true;
		} 
		
		if (dna.length<=i+3) 
		{
			return false;
		}
		
		String actualString1 = dna[i+1];
		String actualString2 = dna[i+2];
		String actualString3 = dna[i+3];
		
		if (secondCondition(actualString1,actualString2,actualString3,actualChar,j))
		{
			return true;
		}		

		if (thirdCondition(actualString1,actualString2,actualString3,actualChar,j))
		{
			return true;
		}

		if (fourthCondition(actualString1,actualString2,actualString3,actualChar,j))
		{
			return true;
		}
		
		
		return false;
	}
	
	private boolean amIAMutant() 
	{
		return elements.length()>1;
	}
	
	public boolean isMutant(String[] dna) 
	{
		
		for (int i =0;i< dna.length;i++) 
		{
			String actualString = dna[i];
			for(int j=0;j<actualString.length();j++) 
			{
				char actualChar= actualString.charAt(j);
				
				if (!hasDnaElement(actualChar) && hasMutantSecuence(i,j,dna))
				{
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
