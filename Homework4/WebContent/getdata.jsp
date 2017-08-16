<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="DataInterface.DataReader"%>
<%
	DataReader db;

	db = (DataReader)request.getServletContext().getAttribute("data");

    String input = request.getParameter("q");
    
   // String query =input.substring(input.lastIndexOf(" ")+1);
    
    //System.out.println("input ----> "+input);
    
    //System.out.println("query ----> "+query);
    
   // int pos;
	//pos = input.indexOf(" ");
     
    List<String> countries = db.getData(input);
    
    //Collections.sort(countries,new String());
 
    Iterator<String> iterator = countries.iterator();
    int i = 0;
    while(iterator.hasNext() && i<10) {
        String country = (String)iterator.next();
        out.println(country);
        i++;
    }
%>