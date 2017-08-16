package model;

public class Raw {
	
	private String fileName;
	private String filePath;
	private int sNo;
	
	public Raw(){
		
	}
	
	
	public Raw(String fileName, String filePath, int sNo) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.sNo = sNo;
	}
	
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	
	
	

}
