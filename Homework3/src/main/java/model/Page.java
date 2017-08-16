package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Page implements Serializable{
	
	private int sNo;
	private String id;
	private String url;
	private String title;
	private String path;
	private String crawldate;
	private String bodytext;
	private String description;
	private String keywords;
	private int outlinks;
	private ArrayList<String> metadata;
	private ArrayList<String> img;
	private ArrayList<String> script;
	private ArrayList<String> imports;
	private ArrayList<String> links;
	private ArrayList<String> inlinks;
	private String extractdate;
	
	
	
	public int getOutlinks() {
		return outlinks;
	}
	public void setOutlinks(int outlinks) {
		this.outlinks = outlinks;
	}
	public ArrayList<String> getInlinks() {
		return inlinks;
	}
	public void setInlinks(ArrayList<String> inlinks) {
		this.inlinks = inlinks;
	}
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCrawldate() {
		return crawldate;
	}
	public void setCrawldate(String crawldate) {
		this.crawldate = crawldate;
	}
	public String getBodytext() {
		return bodytext;
	}
	public void setBodytext(String bodytext) {
		this.bodytext = bodytext;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public ArrayList<String> getMetadata() {
		return metadata;
	}
	public void setMetadata(ArrayList<String> metadata) {
		this.metadata = metadata;
	}
	public ArrayList<String> getImg() {
		return img;
	}
	public void setImg(ArrayList<String> img) {
		this.img = img;
	}
	public ArrayList<String> getScript() {
		return script;
	}
	public void setScript(ArrayList<String> script) {
		this.script = script;
	}
	public ArrayList<String> getImports() {
		return imports;
	}
	public void setImports(ArrayList<String> imports) {
		this.imports = imports;
	}
	public ArrayList<String> getLinks() {
		return links;
	}
	public void setLinks(ArrayList<String> links) {
		this.links = links;
	}
	public String getExtractdate() {
		return extractdate;
	}
	public void setExtractdate(String extractdate) {
		this.extractdate = extractdate;
	}
	
}
