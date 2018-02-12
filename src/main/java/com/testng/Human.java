package main.java.com.testng;

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
	
	public boolean isAValidChar(char _char) 
	{
		return _char=='A'||_char=='T' ||_char=='G'||_char=='C';
	}
	
	public boolean isAValidDna(String[] dna) 
	{
		int heigh=dna.length;
		for (int i =0;i< dna.length;i++) 
		{
			String actualString = dna[i];
			if (actualString.length()!=heigh)return false;
		}
		return true;
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
				
				if (isAValidChar(actualChar)) 
				{
					if (!hasDnaElement(actualChar) && mutantAlgorithm.hasMutantSecuence(i,j,dna))
					{
						addDnaElement(actualChar);
						if (amIAMutant()) 
						{
							return true;
						}
					}
				}
				
			}
		}
		
		return false;	
	}
	
}
