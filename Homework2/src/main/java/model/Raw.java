package model;

public class Raw {
	
	private String Url;
	private String Source;
	private String DateTime;
	
	
	
	public Raw() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Raw(String url, String source, String dateTime) {
		super();
		Url = url;
		Source = source;
		DateTime = dateTime;
	}
	
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		this.Url = url;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public String getDateTime() {
		return DateTime;
	}
	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}
	
	

}
