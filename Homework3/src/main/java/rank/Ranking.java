package rank;

import java.awt.List;
import java.util.*;


public class Ranking  {
	public double tf(ArrayList<String> doc, String term) {
	    double result = 0;
	    for (String word : doc) {
	       if (term.equalsIgnoreCase(word))
	              result++;
	       }
	    return result / doc.size();
	}
	public double idf(ArrayList<ArrayList<String>> docs, String term) {
	    double n = 0;
	    for (ArrayList<String> doc : docs) {
	        for (String word : doc) {
	            if (term.equalsIgnoreCase(word)) {
	                n++;
	                break;
	            }
	        }
	    }
	    return Math.log(docs.size() / n);
	}
	
	public static Double idf(int N, int df) {
		Double n = 0.0;
	    
	    return Math.log10(N / df);
	}
	
	public static Double tfidf(int tf, Double idf) {
		Double n = 0.0;
	    
	    return Math.log10(1 + tf)* idf;
	}
	
	public double tfIdf(ArrayList<String> doc, ArrayList<ArrayList<String>> docs, String term) {
	    return tf(doc, term) * idf(docs, term);
	}
	
	public static void main(String[] args) {
		 
		ArrayList<String> doc1 = (ArrayList<String>) Arrays.asList("Lorem", "ipsum", "dolor", "ipsum", "sit", "ipsum");
		ArrayList<String> doc2 = (ArrayList<String>) Arrays.asList("Vituperata", "incorrupte", "at", "ipsum", "pro", "quo");
		ArrayList<String> doc3 = (ArrayList<String>) Arrays.asList("Has", "persius", "disputationi", "id", "simul");
		ArrayList<ArrayList<String>> documents = (ArrayList<ArrayList<String>>) Arrays.asList(doc1, doc2, doc3);
	 
		Ranking calculator = new Ranking();
	    double tfidf = calculator.tfIdf(doc1, documents, "ipsum");
	    System.out.println("TF-IDF (ipsum) = " + tfidf);
	 
	}
}