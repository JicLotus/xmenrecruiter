package main.java.com.responses;

public class StatResponse {
	private long count_mutant_dna;
	private long count_human_dna;
	private float ratio;
	
	public void setCountMutant(long _count_mutant_dna) 
	{
		this.count_mutant_dna=_count_mutant_dna;
	}
	public long getCountMutant()
	{
		return this.count_mutant_dna;
	}
	
	public void setCountHuman(long _count_human_dna) 
	{
		this.count_human_dna=_count_human_dna;
	}
	public long getCountHuman()
	{
		return this.count_human_dna;
	}
	
	public void setRatio(float _ratio) 
	{
		this.ratio=_ratio;
	}
	public float getRatio()
	{
		return this.ratio;
	}
	
}
