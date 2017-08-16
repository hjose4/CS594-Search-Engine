package model;

import java.io.Serializable;
import java.util.ArrayList;

public class PageRank implements Serializable{
	
	private String id;
	private Page p;
	private Double rank; 
	private Double curr;
	private int income;
	private int outgo;
//	private ArrayList<Page> inlinks;
	
	
	
	
	
	public String getId() {
		return id;
	}
	public PageRank(String id, Page p, double rank, double curr, int income, int outgo) {
	super();
	this.id = id;
	this.p = p;
	this.rank = rank;
	this.curr = curr;
	this.income = income;
	this.outgo = outgo;
}
	public void setId(String id) {
		this.id = id;
	}
	public Page getP() {
		return p;
	}
	public void setP(Page p) {
		this.p = p;
	}
	public double getRank() {
		return rank;
	}
	public void setRank(double rank) {
		this.rank = rank;
	}
	public double getCurr() {
		return curr;
	}
	public void setCurr(double curr) {
		this.curr = curr;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public int getOutgo() {
		return outgo;
	}
	public void setOutgo(int outgo) {
		this.outgo = outgo;
	}
//	public ArrayList<Page> getInlinks() {
//		return inlinks;
//	}
//	public void setInlinks(ArrayList<Page> inlinks) {
//		this.inlinks = inlinks;
//	}
	
	
	
}
