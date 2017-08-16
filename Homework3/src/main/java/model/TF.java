package model;

public class TF {
	
	private String word;
	private int count;
	
	
	public TF(String word, int count) {
		super();
		this.word = word;
		this.count = count;
	}
	
	public TF() {
		// TODO Auto-generated constructor stub
	}

	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	

}
