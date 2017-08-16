package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Doc {
	
	private int sNo;
	private String id;
	private ArrayList<TF> wordList; 
	
	public int getsNo() {
		return sNo;
	}

	public void setsNo(int sNo) {
		this.sNo = sNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<TF> getWordList() {
		return wordList;
	}

	public void setWordList(ArrayList<TF> wordList) {
		this.wordList = wordList;
	}

	public Doc() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
