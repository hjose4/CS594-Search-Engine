package model;

import java.io.Serializable;

public class TDF implements Serializable{
	
	private int sNO;
	private String id;
	private int count;
	private Double score;
	
	
	public TDF(int sNO, String id, int count, Double score) {
		super();
		this.sNO = sNO;
		this.id = id;
		this.count = count;
		this.score = score;
		
	}
	
	
	
	
	
	

	public TDF() {
		// TODO Auto-generated constructor stub
	}







	public Double getScore() {
		return score;
	}




	public void setScore(Double score) {
		this.score = score;
	}




	public int getsNO() {
		return sNO;
	}
	public void setsNO(int sNO) {
		this.sNO = sNO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
